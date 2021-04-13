package com.mitou.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 分页查询--系统用户 的请求参数
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@ApiModel(description = "登录")
public class BaseUserLoginDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String userPwd;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
