package com.mitou.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 * 角色权限关系
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@TableName("base_role_menu")
public class BaseRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色菜单ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "ROLE_MENU_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "角色菜单ID", position = 0)
    private Long roleMenuId;

    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("ROLE_ID")
    @ApiModelProperty(value = "角色ID", position = 1)
    private Long roleId;

    /**
     * 菜单ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("MENU_ID")
    @ApiModelProperty(value = "菜单ID", position = 2)
    private Long menuId;

}
