package com.mitou.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;

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
     * 姓名
     */
    @TableField("USER_NAME")
    @ApiModelProperty(value = "姓名", position = 1)
    private String userName;

    /**
     * 密码
     */
    @TableField("USER_PWD")
    @ApiModelProperty(value = "密码", position = 2)
    private String userPwd;

    /**
     * 电话
     */
    @TableField("PHONE")
    @ApiModelProperty(value = "电话", position = 3)
    private String phone;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    @ApiModelProperty(value = "邮箱", position = 4)
    private String email;

    /**
     * 性别(0:男 1:女)
     */
    @TableField("GENDER")
    @ApiModelProperty(value = "性别(0:男 1:女)", position = 5)
    private Integer gender;

    /**
     * 用户头像
     */
    @TableField("USER_LOGO")
    @ApiModelProperty(value = "用户头像", position = 6)
    private String userLogo;

    /**
     * 生日
     */
    @TableField("BIRTHDAY")
    @ApiModelProperty(value = "生日", position = 7)
    private Date birthday;

    /**
     * 单位(组织id)
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("ORG_ID")
    @ApiModelProperty(value = "单位(组织id)", position = 8)
    private Long orgId;

    /**
     * 单位(组织)
     */
    @TableField("ORG_NAME")
    @ApiModelProperty(value = "单位(组织)", position = 9)
    private String orgName;

    /**
     * 职位
     */
    @TableField("POSITION")
    @ApiModelProperty(value = "职位", position = 10)
    private String position;

    /**
     * 部门
     */
    @TableField("DEPT")
    @ApiModelProperty(value = "部门", position = 11)
    private String dept;

    /**
     * 主系统登录名
     */
    @TableField("MAIN_USER_ID")
    @ApiModelProperty(value = "主系统登录名", position = 12)
    private String mainUserId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getMainUserId() {
        return mainUserId;
    }

    public void setMainUserId(String mainUserId) {
        this.mainUserId = mainUserId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }


}
