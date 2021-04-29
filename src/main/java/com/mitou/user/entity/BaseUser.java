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
 * 系统用户
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@TableName("base_user")
public class BaseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "USER_ID", type = IdType.AUTO)
    @ApiModelProperty(value = "用户ID", position = 0)
    private Long userId;

    /**
     * 登录名
     */
    @TableField("LOGIN_NAME")
    @ApiModelProperty(value = "登录名", position = 1)
    private String loginName;

    /**
     * 姓名
     */
    @TableField("USER_NAME")
    @ApiModelProperty(value = "姓名", position = 2)
    private String userName;

    /**
     * 密码
     */
    @TableField("USER_PWD")
    @ApiModelProperty(value = "密码", position = 3)
    private String userPwd;

    /**
     * 电话
     */
    @TableField("PHONE")
    @ApiModelProperty(value = "电话", position = 4)
    private String phone;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    @ApiModelProperty(value = "邮箱", position = 5)
    private String email;

    /**
     * 性别
     */
    @TableField("GENDER")
    @ApiModelProperty(value = "性别", position = 6)
    private String gender;

    /**
     * 用户头像
     */
    @TableField("USER_LOGO")
    @ApiModelProperty(value = "用户头像", position = 7)
    private String userLogo;

    /**
     * 生日
     */
    @TableField("BIRTHDAY")
    @ApiModelProperty(value = "生日", position = 8)
    private Date birthday;

    /**
     * 组织id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("ORG_ID")
    @ApiModelProperty(value = "组织id", position = 9)
    private Long orgId;

    /**
     * 组织
     */
    @TableField("ORG_NAME")
    @ApiModelProperty(value = "组织", position = 10)
    private String orgName;

    /**
     * 职位
     */
    @TableField("POSITION")
    @ApiModelProperty(value = "职位", position = 11)
    private String position;

    /**
     * 部门
     */
    @TableField("DEPT")
    @ApiModelProperty(value = "部门", position = 12)
    private String dept;

    /**
     * 用户类型
     */
    @TableField("USER_TYPE")
    @ApiModelProperty(value = "用户类型", position = 13)
    private String userType;

    /**
     * 创建人ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("CREATE_USER_ID")
    @ApiModelProperty(value = "创建人ID", position = 14)
    private Long createUserId;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @ApiModelProperty(value = "创建时间", position = 15)
    private Date createTime;

    /**
     * 删除状态( 0:已删除 1:正常)
     */
    @TableField("DEL_FLAG")
    @ApiModelProperty(value = "删除状态( 0:已删除 1:正常)", position = 16)
    private Integer delFlag;

}
