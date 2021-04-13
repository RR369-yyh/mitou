package com.mitou.user.entity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 分页查询--系统角色 的请求参数 vo
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@ApiModel(description = "系统角色分页条件Vo")
public class BaseRoleQuery {

    private static final long serialVersionUID = 1L;
    
    
    @ApiModelProperty(value = "角色名称", position = 1)
    private String roleName;

    @ApiModelProperty(value = "是否内置角色( 0:非内置 1:内置)", position = 2)
    private Integer sysInit;
    
    @ApiModelProperty(value = "删除状态( 0:已删除 1:正常)", position = 3)
    private Integer delFlag;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getSysInit() {
        return sysInit;
    }

    public void setSysInit(Integer sysInit) {
        this.sysInit = sysInit;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
