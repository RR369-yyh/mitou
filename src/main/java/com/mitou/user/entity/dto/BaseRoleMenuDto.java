package com.mitou.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 角色菜单关系
 * </p>
 *
 * @author rice
 * @since 2021-03-24
 */
@Data
@ApiModel(description = "角色菜单关系")
public class BaseRoleMenuDto implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    @ApiModelProperty(value = "菜单ID集合", required = true)
    private List<Long> menuIdList;

}
