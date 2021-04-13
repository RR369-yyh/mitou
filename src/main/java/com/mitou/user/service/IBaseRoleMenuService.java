package com.mitou.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitou.user.response.Result;
import com.mitou.user.entity.BaseRoleMenu;
import com.mitou.user.entity.query.BaseRoleMenuQuery;

/**
 * <p>
 * 角色权限关系 服务接口类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
public interface IBaseRoleMenuService extends IService<BaseRoleMenu> {

    Result<BaseRoleMenu> selectByPrimaryKey(Long roleMenuId);

    Result<Page<BaseRoleMenu>> select(BaseRoleMenuQuery baseRoleMenuQuery, Integer pageNo, Integer pageSize);

    Result insert(BaseRoleMenu baseRoleMenu);

    Result updateByPrimaryKeySelective(BaseRoleMenu baseRoleMenu);

    Result deleteByPrimaryKey(Long roleMenuId);
}
