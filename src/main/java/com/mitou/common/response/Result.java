package com.mitou.common.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 返回结果统一封装
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@Slf4j
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 3485147337688330883L;

    @ApiModelProperty(value = "状态码", example = "1")
    private Integer code;
    @ApiModelProperty(value = "描述")
    private String msg;
    @ApiModelProperty(value = "数据")
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
        return result;
    }

    public static <T> Result<T> failure(ResultCode resultCode, T data) {
        Result<T> result = new Result<>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.msg();
    }
}
