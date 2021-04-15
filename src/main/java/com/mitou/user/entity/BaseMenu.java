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
 * 系统菜单
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@TableName("base_menu")
public class BaseMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "MENU_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "菜单ID", position = 0)
    private Long menuId;

    /**
     * 菜单父ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("PARENT_MENU_ID")
    @ApiModelProperty(value = "菜单父ID", position = 1)
    private Long parentMenuId;

    /**
     * 菜单名称
     */
    @TableField("MENU_NAME")
    @ApiModelProperty(value = "菜单名称", position = 2)
    private String menuName;

    /**
     * 菜单编码
     */
    @TableField("MENU_CODE")
    @ApiModelProperty(value = "菜单编码", position = 3)
    private String menuCode;

    /**
     * 菜单URL
     */
    @TableField("MENU_URL")
    @ApiModelProperty(value = "菜单URL", position = 4)
    private String menuUrl;

    /**
     * 菜单类型( 0:目录 1:菜单 2:按钮)
     */
    @TableField("MENU_TYPE")
    @ApiModelProperty(value = "菜单类型( 0:目录 1:菜单 2:按钮)", position = 5)
    private Integer menuType;

    /**
     * 显示顺序
     */
    @TableField("ORDER_NUM")
    @ApiModelProperty(value = "显示顺序", position = 6)
    private Integer orderNum;

    /**
     * 创建人
     */
    @TableField("CREATE_USER_NAME")
    @ApiModelProperty(value = "创建人", position = 7)
    private String createUserName;

    /**
     * 创建人ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("CREATE_USER_ID")
    @ApiModelProperty(value = "创建人ID", position = 8)
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @ApiModelProperty(value = "创建时间", position = 9)
    private Date createTime;

    /**
     * 删除状态( 0:已删除 1:正常)
     */
    @TableField("DEL_FLAG")
    @ApiModelProperty(value = "删除状态( 0:已删除 1:正常)", position = 10)
    private Integer delFlag;

}
