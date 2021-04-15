package com.mitou.common.exception;

import com.mitou.common.response.Result;
import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局异常处理
 *
 * @author rice
 * @since 2021-03-25
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 异常统一处理
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleError(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        logger.info("全局异常处理");
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        exception.printStackTrace(pw);
        sw.flush();
        pw.flush();
        logger.error("请求方式：{}，请求地址：{}，请求者ip:{}，服务器异常:{}",request.getMethod(),request.getRequestURI(),request.getRemoteAddr(), System.getProperty("line.separator") + sw.toString());
        //404异常
        if(exception instanceof NoHandlerFoundException){
            response.setStatus(Response.SC_NOT_FOUND);
//            return Result.fail.error("资源不存在！！").msg(exception.getMessage());
        }
        if(exception instanceof TypeMismatchException || exception instanceof HttpMessageNotReadableException){
            response.setStatus(Response.SC_BAD_REQUEST);
//            return Result.fail.error("参数不正确！！").msg(exception.getMessage());
        }
        response.setStatus(Response.SC_INTERNAL_SERVER_ERROR);
        return Result.failure(exception.getMessage());
    }

}
