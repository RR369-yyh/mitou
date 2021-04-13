package com.mitou.user.response;

/**
 * 定义返回值
 *
 * @author rice
 * @since 2021-03-25
 */
public enum ResultCodeEnum {

    /**
     * 调用成功
     */
    SUCCESS(0, "成功"),

    /**
     * 调用失败
     */
    FAIL(-1, "失败");


    /**
     * 返回编码
     */
    private Integer code;

    /**
     * 编码对应的消息
     */
    private String msg;

    ResultCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取枚举类型的编码值
     */
    public Integer code() {
        return this.code;
    }

    /**
     * 获取枚举类型的编码含义
     */
    public String msg() {
        return this.msg;
    }


}
