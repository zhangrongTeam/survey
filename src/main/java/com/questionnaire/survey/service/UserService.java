package com.questionnaire.survey.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.questionnaire.survey.config.Wechat;
import com.questionnaire.survey.constant.Constant;
import com.questionnaire.survey.constant.ErrorCode;
import com.questionnaire.survey.constant.ProjectStatus;
import com.questionnaire.survey.constant.UserType;
import com.questionnaire.survey.entity.Project;
import com.questionnaire.survey.entity.SysDictItem;
import com.questionnaire.survey.entity.User;
import com.questionnaire.survey.interceptor.JwtInterceptor;
import com.questionnaire.survey.mapper.UserMapper;
import com.questionnaire.survey.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.questionnaire.survey.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.questionnaire.survey.constant.ErrorCode.*;
import static com.questionnaire.survey.utils.PasswordUtil.entryptPassword;
import static com.questionnaire.survey.utils.RestResult.fail;
import static com.questionnaire.survey.utils.RestResult.success;
import static com.xiaoleilu.hutool.lang.Base64.decodeStr;
import static com.xiaoleilu.hutool.lang.Base64.encode;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-01
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User>{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysDictItemService sysDictItemService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //微信用户信息录入(新用户则录入，老用户则查询) 注册
    public RestResult<User> registered(User wxUser) {
        if (StringUtils.isBlank(wxUser.getId()) || StringUtils.isBlank(wxUser.getOpenId())) {
            return fail(EXCEPTION_ILLEGAL_ARGUMENT);
        }
        wxUser.updateById();
        return success(wxUser);
    }

    public RestResult<User> wxAuthorize(String code) {
        User wxUser = new User();
        JSONObject userInfo = WechatUtils.getUserInfo(Wechat.APPID, Wechat.APPSECRET, code);
        String openid = userInfo.getString("openid");
        String unionid = userInfo.getString("unionid");
        wxUser.setOpenId(openid);
        wxUser.setUnionId(unionid);
        wxUser.setUserType(UserType.USER.getTypeCode());
        User wx = userMapper.selectOne(new User().setOpenId(openid));
        if (wx == null) {
            wxUser.insert();
            return success(wxUser);
        }
        return success(wx);
    }

    public RestResult<User> getUserDetail(String openId) {
        User wx = userMapper.selectOne(new User().setOpenId(openId));
        if (wx == null) {
            wx.insert();
        }
        return success(wx);
    }

    //管理后台用户登录
    public RestResult<User> loginWeb(User loginUser) {
        //根据用户名查询用户信息
        User user = userMapper.queryUser(loginUser);
        if (user == null) {
            return fail(USER_NOT_EXIST);
        }
        if (!PasswordUtil.validatePassword2(loginUser.getPassword(), user.getPassword())) {
            return fail(USER_AUTH_FAILED);
        }
        //判断是否从管理后台登入并是管理员或者超级管理员
        if (!JwtUtil.isWebUser(user)){
            return fail(ErrorCode.USER_LACK_OF_RIGHT);
        }
        // 用户登录redis key标志
        String redisUserLoginKey = String.format(Constant.SYS_USER_REDIS_PREFIX_FORMAT_KEY, user.getLoginName(), UUID.randomUUID().toString());
        // 取消单点登录，使用经过Base64加密的字符串作为session
        user.setToken(encode(redisUserLoginKey));
        //设置redis超时时间
        stringRedisTemplate.boundValueOps(redisUserLoginKey).set(JwtUtil.encodeCustom(user), JwtInterceptor.SESSION_EXPIRED_TIME, TimeUnit.MINUTES);
        return success(user);
    }

    //管理后台添加用户
    public RestResult<Void> addUser(User user) {
        int count = 0;
        // 填充默认值
        // 默认只能添加管理员
        user.setId(null);
        user.setUserType(UserType.SUPER.getTypeCode());
//        // 如果新密码为空，则不更换密码
        if (isNotBlank(user.getPassword())) {
            user.setPassword(entryptPassword(user.getPassword()));
        }
        user.setLoginDate(LocalDateTime.now());
        if (userMapper.selectOne(new User().setLoginName(user.getLoginName()).setUserType(UserType.SUPER.getTypeCode())) != null) {
            return fail(LOGIN_NAME_DUPLICATE);
        }

        count += userMapper.insert(user);
        if (count > 0) {
            return success(null);
        } else {
            return fail(CRUD_UPDATE_NO_RECORD);
        }
    }

    public RestResult<Void> updateManager(User user) {
        User updateUser = userMapper.selectOne(new User().setLoginName(user.getLoginName()).setUserType(UserType.SUPER.getTypeCode()));
        if (isNotBlank(user.getPassword())) {
            user.setPassword(entryptPassword(user.getPassword()));
        }
        if(updateUser.updateById()){
            return success(null);
        }
        return fail(CRUD_UPDATE_NO_RECORD);
    }

    public RestResult<Void> logout(String sessionId) {
        String rawRedisUserLoginKey = decodeStr(sessionId);
        stringRedisTemplate.delete(rawRedisUserLoginKey);
        return success(null);
    }

}
