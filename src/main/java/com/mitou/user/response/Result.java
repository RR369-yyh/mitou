package com.mitou.user.response;

import java.io.Serializable;

/**
 * 返回结果统一封装
 *
 * @author rice
 * @since 2021-03-25
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6085601505900509075L;

    /**
     * 返回操作码（默认为正常）
     */
    private Integer code = ResultCodeEnum.SUCCESS.code();

    /**
     * 返回数据信息
     */
    private T data;

    /**
     * 返回正确消息信息
     */
    private String msg = "";

    /**
     * 返回错误的消息信息
     */
    private String error = "";

    public static Result success = new Result();
    public static Result fail = new Result().code(ResultCodeEnum.FAIL.code());

    /**
     * 构造方法
     *
     */
    public static Result build() {
        return new Result();
    }

    public static <T> Result<T> build(T data) {
        return new Result().data(data);
    }

    /**
     * 设置返回数据
     * @param data
     * @return
     */
    public Result data(T data) {
        this.data = data;
        return this;
    }

    public Result code(Integer code) {
        this.code = code;
        return this;
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result error(String error) {
        this.error = error;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public String getError() {
        return error;
    }
}
