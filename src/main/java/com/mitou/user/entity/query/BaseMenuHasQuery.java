package com.mitou.user.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户下菜单分页条件Vo
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@ApiModel(description = "用户下菜单分页条件Vo")
public class BaseMenuHasQuery {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单类型( 0:目录 1:菜单 2:按钮)", example = "1", position = 0)
    private Integer menuType;

    @ApiModelProperty(value = "菜单父ID", example = "0", position = 1)
    private Long parentMenuId;

}
