package com.mitou.user.config;

import com.mitou.user.auth.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 *
 * @author rice
 * @since 2021-03-26
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiUri = "/**";
        //注册权限校验拦截器
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns(apiUri);
    }

}
