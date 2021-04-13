package com.mitou.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * <p>
 * 用户角色关系
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@TableName("base_user_role")
public class BaseUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户角色关系ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "USER_ROLE_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "用户角色关系ID", position = 0)
    private Long userRoleId;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("USER_ID")
    @ApiModelProperty(value = "用户ID", position = 1)
    private Long userId;

    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("ROLE_ID")
    @ApiModelProperty(value = "角色ID", position = 2)
    private Long roleId;

    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
