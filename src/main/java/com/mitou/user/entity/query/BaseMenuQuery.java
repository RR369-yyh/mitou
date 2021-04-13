package com.mitou.user.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 分页查询--菜单 的请求参数 vo
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@ApiModel(description = "菜单分页条件Vo")
public class BaseMenuQuery {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "菜单名称", position = 1)
    private String menuName;

    @ApiModelProperty(value = "菜单类型( 0:目录 1:菜单 2:按钮)", position = 2)
    private Integer menuType;

    @ApiModelProperty(value = "删除状态( 0:已删除 1:正常)", position = 3)
    private Integer delFlag;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
