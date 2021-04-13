package com.mitou.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 用户角色关系
 * </p>
 *
 * @author rice
 * @since 2021-03-24
 */
@ApiModel(description = "用户角色关系")
public class BaseUserRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "角色ID集合", required = true)
    private List<Long> roleIdList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }
}
