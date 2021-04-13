package com.mitou.user.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 分页查询--用户角色关系 的请求参数 vo
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@ApiModel(description = "用户角色关系分页条件Vo")
public class BaseUserRoleQuery {

    private static final long serialVersionUID = 1L;
    
    
    @ApiModelProperty(value = "用户ID", position = 1)
    private Long userId;
    
    @ApiModelProperty(value = "角色ID", position = 2)
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
