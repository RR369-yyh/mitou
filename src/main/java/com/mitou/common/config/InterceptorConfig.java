package com.mitou.common.config;

import com.mitou.common.auth.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
        //注册权限校验拦截器
        registry.addInterceptor(new AuthInterceptor())
                //拦截的请求
                .addPathPatterns(
                        "/**"
                )
                //不拦截的请求
                .excludePathPatterns(
                        "/user/register",
                        "/user/login",
                        "/user/logout",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/**",
                        "/doc.html/**"
                );
    }

    /**
     * 使用swagger2需重写此方法，避免被拦截。
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
