package com.mitou.user.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mitou.user.entity.BaseRole;
import io.swagger.annotations.ApiModelProperty;

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
public class BaseUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "用户ID", position = 0)
    private Long userId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", position = 1)
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", position = 2)
    private String userPwd;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", position = 3)
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", position = 4)
    private String email;

    /**
     * 性别(0:男 1:女)
     */
    @ApiModelProperty(value = "性别(0:男 1:女)", position = 5)
    private Integer gender;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", position = 6)
    private String userLogo;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日", position = 7)
    private Date birthday;

    /**
     * 单位(组织id)
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "单位(组织id)", position = 8)
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

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", position = 11)
    private Date createTime;

    /**
     * 角色集
     */
    @ApiModelProperty(value = "角色集", position = 12)
    private List<BaseRole> roleList;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<BaseRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<BaseRole> roleList) {
        this.roleList = roleList;
    }
}
