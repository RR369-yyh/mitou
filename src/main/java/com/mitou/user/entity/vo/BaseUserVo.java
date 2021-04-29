package com.mitou.user.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mitou.user.entity.BaseRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 用户信息返回
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@ApiModel(description = "用户信息返回Vo")
public class BaseUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户ID", position = 0)
    private Long userId;

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", position = 1)
    private String loginName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", position = 2)
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", position = 3)
    private String userPwd;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", position = 4)
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", position = 5)
    private String email;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", position = 6)
    private String gender;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", position = 7)
    private String userLogo;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", position = 8)
    private Date birthday;

    /**
     * 组织id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "组织id", position = 9)
    private Long orgId;

    /**
     * 组织
     */
    @ApiModelProperty(value = "组织", position = 10)
    private String orgName;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位", position = 11)
    private String position;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门", position = 12)
    private String dept;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", position = 13)
    private Date createTime;

    /**
     * 角色集
     */
    @ApiModelProperty(value = "角色集", position = 14)
    private List<BaseRole> roleList;

}
