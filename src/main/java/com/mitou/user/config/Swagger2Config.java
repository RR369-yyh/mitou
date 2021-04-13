package com.mitou.user.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.mitou.user.properties.Swagger2Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Knife4j - swagger增强ui配置
 *
 * @author rice
 * @since 2021-03-25
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config {

    @Resource
    private Swagger2Properties swagger2Properties;

    /**
     * 创建RestApi
     *
     * @return
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).
                useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger2Properties.getScanBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .apiInfo(
                        new ApiInfoBuilder()
                                .version("1.0")
                                .title(swagger2Properties.getServerTitle())
                                .description("<div style='font-size:14px;color:red;'>" + swagger2Properties.getServerTitle() + " RESTful APIs</div>")
                                .build()
                );
    }

    /**
     * 配置全局的参数
     *
     * @return
     */
    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> securitySchemes = new ArrayList<SecurityScheme>();
        securitySchemes.add(new ApiKey("Authorization", "Authorization", "header"));
        return securitySchemes;
    }

    /**
     * 通过正则表达式，设置需要使用参数的接口（或者说，是去除掉不需要使用参数的接口）
     *
     * @return
     */
    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^((?!auth).)*$"))
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }
}
