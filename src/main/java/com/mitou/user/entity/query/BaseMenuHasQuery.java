package com.mitou.user.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 用户下菜单分页条件Vo
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@ApiModel(description = "用户下菜单分页条件Vo")
public class BaseMenuHasQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单类型( 0:目录 1:菜单 2:按钮)", position = 0)
    private Integer menuType;

    @ApiModelProperty(value = "菜单父ID", position = 1)
    private Long parentMenuId;

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }
}
