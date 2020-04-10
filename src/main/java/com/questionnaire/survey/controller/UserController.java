package com.questionnaire.survey.controller;


import com.questionnaire.survey.DTO.LoginDTO;
import com.questionnaire.survey.constant.Constant;
import com.questionnaire.survey.entity.User;
import com.questionnaire.survey.service.UserService;
import com.questionnaire.survey.utils.RestResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.questionnaire.survey.utils.BeanUtil.copy;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangrong123
 * @since 2020-04-01
 */
@RestController
@RequestMapping("/survey/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation("管理后台用户登录")
    public RestResult<User> login(@Valid @RequestBody LoginDTO loginUser) {
        return userService.loginWeb(copy(loginUser, User.class));
    }


    @PostMapping("/logout")
    @ApiOperation("管理后台用户登出")
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> logout() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String sessionId = request.getHeader(AUTHORIZATION);
        request.removeAttribute(Constant.USER);
        return userService.logout(sessionId);
    }

    @PostMapping(path = "/wxAuthorize", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("微信授权")
    public ResponseEntity<RestResult<User>> wxAuthorize(@RequestBody User user) {
        return new ResponseEntity<>(userService.wxAuthorize(user.getCode()), OK);
    }

    @PostMapping(path = "/registered", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("微信用户信息注册")
    public ResponseEntity<RestResult<User>> registered(@RequestBody User wxUser) {
        return new ResponseEntity<>(userService.registered(wxUser), OK);
    }

    @PostMapping(path = "/getUserDetail", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation("微信用户信息详情查询")
    public ResponseEntity<RestResult<User>> getUserDetail(@RequestBody User user) {
        return new ResponseEntity<>(userService.getUserDetail(user.getOpenId()), OK);
    }

    @PostMapping("addManager")
    @ApiOperation("管理后台添加用户")
    //@CustomLog(logDesc = "添加用户",type = CustomLog.LOG_TYPE.ADD)
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> addManager(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("updateManager")
    @ApiOperation("管理后台修改管理用户")
    //@CustomLog(logDesc = "添加用户",type = CustomLog.LOG_TYPE.ADD)
    @ApiImplicitParam(name = "Authorization", value = "token", required = true, dataType = "string", paramType = "header")
    public RestResult<Void> updateManager(@RequestBody User user) {
        return userService.updateManager(user);
    }

}

