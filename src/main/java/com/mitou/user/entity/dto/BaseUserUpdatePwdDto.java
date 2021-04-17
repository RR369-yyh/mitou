package com.mitou.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 更新密码的请求参数
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@ApiModel(description = "更新密码")
public class BaseUserUpdatePwdDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", position = 1)
    private Long userId;

    /**
     * 原密码
     */
    @ApiModelProperty(value = "原密码", position = 2)
    private String oldUserPwd;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", position = 3)
    private String userPwd;

}
