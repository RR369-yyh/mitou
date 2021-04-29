package com.mitou.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitou.common.response.Result;
import com.mitou.user.entity.BaseRole;
import com.mitou.user.entity.dto.BaseUserRoleDto;
import com.mitou.user.entity.query.BaseRoleQuery;

import java.util.List;

/**
 * <p>
 * 系统角色 服务接口类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
public interface IBaseRoleService extends IService<BaseRole> {

    /**
     * 分页查询方法
     *
     * @param baseRoleQuery
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<BaseRole> page(BaseRoleQuery baseRoleQuery, Integer pageNo, Integer pageSize);

    /**
     * 查询此用户下的角色集合
     *
     * @param userId
     * @return
     */
    List<BaseRole> getByUserId(Long userId);

    /**
     * 删除数据，并清除掉与此角色的菜单关联和此角色的用户关联
     *
     * @param roleIds
     * @return
     */
    boolean deleteByIds(List<Long> roleIds);

    /**
     * 为用户设置角色，会覆盖之前的角色
     *
     * @param baseUserRoleDto 关系
     * @return
     */
    boolean rel(BaseUserRoleDto baseUserRoleDto);

    /**
     * 获取默认角色
     *
     * @return
     */
    BaseRole getDefaultRole();
}
