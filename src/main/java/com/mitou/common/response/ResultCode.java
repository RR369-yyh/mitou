package com.mitou.common.response;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一返回状态码
 *
 * @author rice
 * @since 2021-03-24
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_VIOLATION_CONSTRAINT(10002, "参数违反约束错误"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_LOGIN_ERROR(20002, "账号不存在"),
    USER_PWD_ERROR(20003, "密码错误"),

    /* 业务错误：30001-39999 */
    BUSINESS_ERROR(30001, "业务错误"),

    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),
    SYSTEM_NOT_RESOURCE(40002, "请求方法不被支持"),
    SYSTEM_SQL_EXECUTE_ERROR(40003, "MP执行异常，SQL执行失败"),

    /* 数据错误：50001-599999 */
    DATA_IS_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),

    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限");

    private Integer code;

    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public static String getMessage(String name) {
        return ResultCode.valueOf(name).msg;
    }

    public static Integer getCode(String name) {
        return ResultCode.valueOf(name).code;
    }

    @Override
    public String toString() {
        return this.name();
    }

    /***
     * 校验重复的code值
     */
    public static void main(String[] args) {
        ResultCode[] apiResultCodes = ResultCode.values();
        List<Integer> codeList = new ArrayList<Integer>();
        for (ResultCode apiResultCode : apiResultCodes) {
            if (codeList.contains(apiResultCode.code)) {
                System.out.println(apiResultCode.code);
            } else {
                codeList.add(apiResultCode.code());
            }
            System.out.println(apiResultCode.code() + " " + apiResultCode.msg());
        }
    }
}

