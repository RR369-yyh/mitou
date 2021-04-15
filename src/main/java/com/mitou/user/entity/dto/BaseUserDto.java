package com.mitou.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 新增--系统用户 的请求参数
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@ApiModel(description = "新增用户")
public class BaseUserDto {

    private static final long serialVersionUID = 1L;

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
     * 性别
     */
    @ApiModelProperty(value = "性别", position = 5)
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
     * 角色idList
     */
    @ApiModelProperty(value = "角色idList", position = 11)
    private List<Long> roleIdList;

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

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
