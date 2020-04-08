package com.questionnaire.survey.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.servlet.http.HttpServletRequest;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * @Auther: zhangrong
 * @Date: 2019/2/28 13:45
 * @Description:
 */
@Slf4j
public class AspectUtil {

    public static String getArg(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object[] args = joinPoint.getArgs();
            return toJSONString(args);
        } catch (Exception e) {
            log.error("e",e);
        }
        return "";
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 ) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
