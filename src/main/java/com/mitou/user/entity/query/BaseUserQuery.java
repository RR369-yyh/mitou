package com.mitou.user.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 分页查询--系统用户 的请求参数 vo
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Data
@ApiModel(description = "系统用户分页条件Vo")
public class BaseUserQuery {

    private static final long serialVersionUID = 1L;
    
    
    @ApiModelProperty(value = "姓名", position = 1)
    private String userName;
    
    @ApiModelProperty(value = "电话", position = 2)
    private String phone;

    @ApiModelProperty(value = "删除状态( 0:已删除 1:正常)", position = 3)
    private Integer delFlag;

}
