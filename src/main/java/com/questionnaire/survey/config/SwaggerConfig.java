package com.questionnaire.survey.config;

import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangrong
 * @since 2018/7/27 8:47
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 扫描所有带有@ApiOperation注解的方法
     */
    @Bean
    public Docket productApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("平台Api文档")
                .version(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"))
                .build();
    }
}
