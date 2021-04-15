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
import java.util.Date;


/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@TableName("base_role")
public class BaseRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "ROLE_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "角色ID", position = 0)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField("ROLE_NAME")
    @ApiModelProperty(value = "角色名称", position = 1)
    private String roleName;

    /**
     * 是否默认角色( 0:非默认 1:默认)
     */
    @TableField("ROLE_DEFAULT")
    @ApiModelProperty(value = "是否默认角色( 0:非默认 1:默认)", position = 2)
    private Integer roleDefault;

    /**
     * 显示顺序
     */
    @TableField("ORDER_NUM")
    @ApiModelProperty(value = "显示顺序", position = 3)
    private Integer orderNum;

    /**
     * 是否内置角色( 0:非内置 1:内置)
     */
    @TableField("SYS_INIT")
    @ApiModelProperty(value = "是否内置角色( 0:非内置 1:内置)", position = 4)
    private Integer sysInit;

    /**
     * 创建人
     */
    @TableField("CREATE_USER_NAME")
    @ApiModelProperty(value = "创建人", position = 5)
    private String createUserName;

    /**
     * 创建人ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("CREATE_USER_ID")
    @ApiModelProperty(value = "创建人ID", position = 6)
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @ApiModelProperty(value = "创建时间", position = 7)
    private Date createTime;

    /**
     * 删除状态( 0:已删除 1:正常)
     */
    @TableField("DEL_FLAG")
    @ApiModelProperty(value = "删除状态( 0:已删除 1:正常)", position = 8)
    private Integer delFlag;

}
