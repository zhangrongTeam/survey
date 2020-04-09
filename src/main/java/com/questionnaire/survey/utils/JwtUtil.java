package com.questionnaire.survey.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.questionnaire.survey.constant.Constant;
import com.questionnaire.survey.constant.UserType;
import com.questionnaire.survey.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.joda.time.DateTime;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Date;

import static io.jsonwebtoken.CompressionCodecs.GZIP;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author zhangrong
 * @since 2018/7/27 8:51
 */
//@Slf4j
public class JwtUtil {

    /**
     * 默认的Jwt token密钥
     */
    private static final String JWT_KEY = "4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2";

    private static final JwtBuilder JWT_BUILDER = Jwts.builder();
    private static final JwtParser JWT_PARSER = Jwts.parser();
    private static final Gson GSON = new GsonBuilder().create();

    /**
     * 初始化JWT builder和parser
     */
    private static void init() {
        Key key = null;
        try {
            key = new SecretKeySpec(
                    JWT_KEY.getBytes("UTF-8"), HS256.getJcaName());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JWT_BUILDER.compressWith(GZIP).signWith(HS256, key);

        JWT_PARSER.setSigningKey(key);
    }

    static {
        init();
    }

    /**
     * 修改默认token不再设置超时时间，超时通过redis的超时时间控制
     * @param t 需要填充的对象实体
     * @param <T> 需要填充的对象类型
     * @return 返回经过对象填充的jwt token字符串
     */
    public static <T> String encodeCustom(T t) {
        return encodeWithExpiredTime(t, DateTime.now().plusYears(5).toDate());
    }

    @SuppressWarnings("unused")
	private static <T> String encodeWithoutExpiredTime(T t) {
        return JWT_BUILDER
                .setSubject(GSON.toJson(t))
                .compact();
    }

    public static <T> String encodeWithExpiredTime(T t, Date expiredDate) {
        return JWT_BUILDER
                .setSubject(GSON.toJson(t))
                .setExpiration(expiredDate)
                .compact();
    }

    public static <T> T decode(String jwtToken, Class<T> jwtClass) {
        String subject = JWT_PARSER
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();

        return GSON.fromJson(subject, jwtClass);
    }

    /**
     * 返回当前请求的用户信息
     * @return 返回当前JwtToken所关联的用户对象
     */
    public static User getCurrentUser() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return (User) request.getAttribute(Constant.USER);
    }

    /**
     * 返回当前请求的用户前台token
     * @return 返回当前JwtToken
     */
    public static String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request.getHeader(AUTHORIZATION);
    }

    /**
     * 返回当前JwtToken所关联的用户是否为超级管理员
     * @return true如果当前用户是超级管理员，false则当前用户不是超级管理员
     */
    public static Boolean isSuperUser() {
        return isSuperUser(getCurrentUser());
    }

    /**
     * 返回传参的用户是否为超级管理员
     * @return true如果当前传参的用户是超级管理员，false则当前传参的用户不是超级管理员
     */
    public static Boolean isSuperUser(User user) {
        System.out.println(user.getUserType());
        return user != null && UserType.SUPER.getTypeCode().equals(user.getUserType());
    }


        /**
         * 判断用户是否是web端用户且是不是管理员
         * @param user 用户
         * @return java.lang.Boolean
         * @author zhangr
         * @date 2019/8/2 11:54
         */
    public static Boolean isWebUser(User user) {
        Assert.notNull(user, "登录用户不能为空");
        return UserType.SUPER.getTypeCode().equals(user.getUserType());
    }

}
