package com.questionnaire.survey.interceptor;

import com.baomidou.mybatisplus.toolkit.Sequence;
import com.questionnaire.survey.entity.User;
import com.questionnaire.survey.utils.AspectUtil;
import com.questionnaire.survey.utils.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.alibaba.fastjson.JSON.toJSONString;
import static com.questionnaire.survey.utils.JwtUtil.getCurrentUser;
import static com.questionnaire.survey.utils.RestResult.OPT_SUCCESS_STATUS;

/**
 * @author zhangrong
 * @since 2018/7/27 10:53
 */
@Aspect
@Configuration
@Slf4j
@Profile({"test", "pro", "dev"})
public class LogAspect {

    @SuppressWarnings("unused")
    private static final Sequence SEQUENCE = new Sequence();

    /**
     * 切入所有带有@ApiOperation注解的方法
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    void doController() {

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Around("doController()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        String uri = request.getRequestURI();
        String method = request.getMethod();

        String params = "";
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        if (uri.contains("/file/")){
            return result;
        }
        params = AspectUtil.getArg(joinPoint);
        long endTime = System.currentTimeMillis();

        if (result != null && result instanceof RestResult) {
            // 如果当前返回结果的数据项为空，且操作成功则自动填充空的数组
            RestResult restResult = ((RestResult) result);

            if (OPT_SUCCESS_STATUS.equals(restResult.getStatus())
                    && restResult.getResultData() == null) {
                //noinspection unchecked
                restResult.setResultData(new String[0]);
            }
        }

        User orgUser = getCurrentUser();

        // 如果为登陆接口，则请求用户置空
        String loginName = orgUser != null ? orgUser.getLoginName() : "无";

        log.debug("\n请求用户:{} 请求IP:{} 请求地址:{} 请求方法:{} 请求费时:{}ms\n请求参数:{}\n请求结果:{}",
                loginName, AspectUtil.getIpAddress(request), uri, method, endTime - startTime, params, toJSONString(result));

        return result;
    }
}
