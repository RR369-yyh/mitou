package com.mitou.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 更新--系统用户 的请求参数
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@ApiModel(description = "更新用户")
public class BaseUserUpdateDto {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", position = 1)
    private String userName;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", position = 2)
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", position = 3)
    private String email;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", position = 4)
    private String gender;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", position = 5)
    private String userLogo;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", position = 6)
    private Date birthday;

    /**
     * 单位(组织id)
     */
    @ApiModelProperty(value = "单位(组织id)", position = 7)
    private Long orgId;

    /**
     * 单位(组织)
     */
    @ApiModelProperty(value = "单位(组织)", position = 8)
    private String orgName;

    /**
     * 职位
     */
    @ApiModelProperty(value = "职位", position = 9)
    private String position;

    /**
     * 部门
     */
    @ApiModelProperty(value = "部门", position = 10)
    private String dept;

}
