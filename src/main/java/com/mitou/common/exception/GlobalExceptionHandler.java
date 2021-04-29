package com.mitou.common.exception;

import com.mitou.common.exception.business.BusinessException;
import com.mitou.common.response.Result;
import com.mitou.common.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 *
 * @author rice
 * @since 2021-03-25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 400：违反约束异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Object> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        this.printErrorLog(e, request);
        return Result.failure(ResultCode.PARAM_VIOLATION_CONSTRAINT);
    }

    /**
     * 400：验证参数封装错误时异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        this.printErrorLog(e, request);
        return Result.failure(ResultCode.PARAM_IS_INVALID);
    }

    /**
     * 400：参数绑定时异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result<Object> handleBindException(BindException e, HttpServletRequest request) {
        this.printErrorLog(e, request);
        return Result.failure(ResultCode.PARAM_IS_INVALID);
    }

    /**
     * 400：使用@Validated注解时，参数验证错误异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        this.printErrorLog(e, request);
        return Result.failure(ResultCode.PARAM_IS_INVALID);
    }

    /**
     * 400：处理请求方法不被支持异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        this.printErrorLog(e, request);
        return Result.failure(ResultCode.SYSTEM_NOT_RESOURCE);
    }

    /**
     * 200：自定义业务异常处理
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Object> handleBusinessException(BusinessException e, HttpServletRequest request) {
        this.printErrorLog(e, request);
        return Result.failure(e.getResultCode());
    }

    /**
     * 500：处理系统异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Result<Object> handleThrowable(Throwable e, HttpServletRequest request) {
        this.printErrorLog(e, request);
        return Result.failure(ResultCode.SYSTEM_INNER_ERROR);
    }

    /**
     * 打印异常信息
     *
     * @param e       异常
     * @param request 请求
     */
    private void printErrorLog(Throwable e, HttpServletRequest request) {
        log.error("请求方式：{}，请求地址：{}，请求ip：{}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        log.error("异常信息：", e);
    }
}
