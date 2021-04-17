package com.mitou.user.entity.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 保存--菜单 的返回参数 vo
 * </p>
 *
 * @author rice
 * @since 2021-03-24
 */
@Data
@ApiModel(description = "菜单列表返回Vo")
public class BaseMenuTreeVo implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "菜单ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;

    @ApiModelProperty(value = "菜单父ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentMenuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单编码")
    private String menuCode;

    @ApiModelProperty(value = "菜单URL")
    private String menuUrl;

    @ApiModelProperty(value = "菜单类型( 0:目录 1:菜单 2:按钮)")
    private Integer menuType;

    @ApiModelProperty(value = "显示顺序")
    private Integer orderNum;

    @ApiModelProperty(value = "创建人")
    private String createUserName;

    @ApiModelProperty(value = "创建人ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "删除状态( 0:已删除 1:正常)")
    private Integer delFlag;

    @ApiModelProperty(value = "菜单子集")
    private List<BaseMenuTreeVo> childrenList;

}
