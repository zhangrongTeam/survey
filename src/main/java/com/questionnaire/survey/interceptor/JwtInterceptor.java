package com.questionnaire.survey.interceptor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.questionnaire.survey.constant.ErrorCode;
import com.questionnaire.survey.entity.User;
import com.questionnaire.survey.utils.JwtUtil;
import com.questionnaire.survey.utils.RestResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

import static com.xiaoleilu.hutool.lang.Base64.decodeStr;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * @author zhangrong
 * @since 2018/7/27 8:50
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    /**
     * 默认session的超时时间为30分钟*24
     */
    public static final long SESSION_EXPIRED_TIME = 30 * 24;

    private static final Gson GSON = new GsonBuilder().create();

    private final StringRedisTemplate stringRedisTemplate;

    public JwtInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 通过OPTIONS请求，防止被权限Interceptor拦截
        if (CorsUtils.isPreFlightRequest(request)) {
            return true;
        }

        // 防止部分浏览器阻止Cors请求再Cors有效期内
        response.addHeader("Access-Control-Allow-Origin", "*");

        String sessionId = request.getHeader(AUTHORIZATION);

        if (StringUtils.isBlank(sessionId)) {
            response.setStatus(UNAUTHORIZED.value());
            response.setHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(GSON.toJson(RestResult.fail(ErrorCode.JWT_NOT_EXIST)));
            return false;
        }

        String rawRedisUserLoginKey = decodeStr(sessionId);

        String token = stringRedisTemplate.boundValueOps(rawRedisUserLoginKey).get();

        if (!StringUtils.isNotBlank(token)) {
            response.setStatus(UNAUTHORIZED.value());
            response.setHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
            response.getWriter().write(GSON.toJson(RestResult.fail(ErrorCode.JWT_ILLEGAL_TOKEN)));
            return false;
        }

        User user = JwtUtil.decode(token, User.class);

        stringRedisTemplate.boundValueOps(rawRedisUserLoginKey)
                .expire(SESSION_EXPIRED_TIME, TimeUnit.MINUTES);

        request.setAttribute("user", user);

        return true;

    }
}

