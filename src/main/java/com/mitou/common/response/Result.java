package com.mitou.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 返回结果统一封装
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@Slf4j
public class Result<T> implements GlobalResult {

    private static final long serialVersionUID = -8806496761426307839L;

    @ApiModelProperty(value = "状态码", example = "1")
    private Integer code;
    @ApiModelProperty(value = "描述")
    private String msg;
    @ApiModelProperty(value = "对象")
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode) {
        Result<T> result = new Result<>();
        result.setResultCode(resultCode);
        log.error("系统内部错误码：" + result.getCode() + "；错误详细描述：" + result.getMsg());
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setResultCode(resultCode);
        result.setData(data);
        log.error("系统内部错误码：" + result.getCode() + "；错误详细描述：" + data.toString());
        return result;
    }

    public static <T> Result<T> failure(String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SYSTEM_INNER_ERROR.code());
        result.setMsg(message);
        log.error("系统内部错误码：" + result.getCode() + "；错误详细描述：" + result.getMsg());
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}
