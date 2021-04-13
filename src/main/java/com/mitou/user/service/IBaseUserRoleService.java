package com.mitou.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitou.user.response.Result;
import com.mitou.user.entity.BaseUserRole;
import com.mitou.user.entity.query.BaseUserRoleQuery;

/**
 * <p>
 * 用户角色关系 服务接口类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
public interface IBaseUserRoleService extends IService<BaseUserRole> {

    Result<BaseUserRole> selectByPrimaryKey(Long userRoleId);

    Result<Page<BaseUserRole>> select(BaseUserRoleQuery baseUserRoleQuery, Integer pageNo, Integer pageSize);

    Result insert(BaseUserRole baseUserRole);

    Result updateByPrimaryKeySelective(BaseUserRole baseUserRole);

    Result deleteByPrimaryKey(Long userRoleId);
}
