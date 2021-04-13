package com.mitou.user.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 分页查询--角色权限关系 的请求参数 vo
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@ApiModel(description = "角色权限关系分页条件Vo")
public class BaseRoleMenuQuery {

    private static final long serialVersionUID = 1L;
    
    
    @ApiModelProperty(value = "角色ID", position = 1)
    private Long roleId;
    
    @ApiModelProperty(value = "菜单ID", position = 2)
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
