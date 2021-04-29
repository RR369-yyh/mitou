package com.mitou.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域设置
 *
 * @author rice
 * @since 2021-04-28
 */
@Configuration
public class CorsConfig implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, req.getHeader("Origin"));
        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        res.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
        res.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,PUT,DELETE,OPTIONS,HEAD");
        //针对于OPTIONS预检请求的有效期，单位为秒,在此期间不用发出另一条预检请求。
        res.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "1800");
        //跨域请求时，浏览器会先发送一个OPTIONS(释义：预检请求)请求，用以判断实际发送的请求是否安全。但此请求不携带自定义头(Authorization)信息，所以此处直接返回ok，不再向下传递。
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setStatus(HttpStatus.OK.value());
            return;
        }
        chain.doFilter(request, response);
    }
}