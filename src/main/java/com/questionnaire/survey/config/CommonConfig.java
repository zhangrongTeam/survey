package com.questionnaire.survey.config;

import com.questionnaire.survey.interceptor.JwtInterceptor;
import com.questionnaire.survey.interceptor.LogAspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zhangrong
 * @since 2018/7/27 8:45
 */
@Configuration
@Import({CorsConfig.class, JsonConfig.class, SwaggerConfig.class,
        GlobalExceptionConfig.class, InterceptorConfig.class, LogAspect.class,
        JwtInterceptor.class, RedisConfig.class,MybatisPlusConfig.class})
public class CommonConfig {

}
