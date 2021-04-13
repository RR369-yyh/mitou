package com.mitou.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitou.user.response.Result;
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

    Result<BaseRole> selectByPrimaryKey(Long roleId);

    Result<Page<BaseRole>> select(BaseRoleQuery baseRoleQuery, Integer pageNo, Integer pageSize);

    Result<List<BaseRole>> selectByUserId(Long userId);

    Result insert(BaseRole baseRole);

    Result updateByPrimaryKeySelective(BaseRole baseRole);

    Result deleteByPrimaryKey(Long roleId);

    /**
     * 为用户设置角色，会覆盖之前的角色
     *
     * @param baseUserRoleDto 关系
     * @return
     */
    Result rel(BaseUserRoleDto baseUserRoleDto);

    /**
     * 获取默认角色
     *
     * @return
     */
    Result<BaseRole> getDefaultRole();
}
