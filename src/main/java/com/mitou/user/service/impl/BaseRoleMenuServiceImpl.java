package com.mitou.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitou.user.response.Result;
import com.mitou.user.entity.BaseRoleMenu;
import com.mitou.user.entity.query.BaseRoleMenuQuery;
import com.mitou.user.mapper.BaseRoleMenuMapper;
import com.mitou.user.service.IBaseRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 角色权限关系 服务实现类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Service
public class BaseRoleMenuServiceImpl extends ServiceImpl<BaseRoleMenuMapper, BaseRoleMenu> implements IBaseRoleMenuService {

    @Resource
    private BaseRoleMenuMapper baseRoleMenuMapper;

    @Override
    public Result<BaseRoleMenu> selectByPrimaryKey(Long roleMenuId) {
        return Result.build(super.getById(roleMenuId));
    }

    @Override
    public Result<Page<BaseRoleMenu>> select(BaseRoleMenuQuery baseRoleMenuQuery, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<BaseRoleMenu> lqw = new LambdaQueryWrapper<>();
        //进行条件组装

        //分页对象
        Page<BaseRoleMenu> page = new Page<>(pageNo, pageSize);
        super.page(page, lqw);
        return Result.build(page);
    }

    @Override
    public Result insert(BaseRoleMenu baseRoleMenu) {
        return Result.build(super.save(baseRoleMenu));
    }

    @Override
    public Result updateByPrimaryKeySelective(BaseRoleMenu baseRoleMenu) {
        return Result.build(super.updateById(baseRoleMenu));
    }

    @Override
    public Result deleteByPrimaryKey(Long roleMenuId) {
        return Result.build(super.removeById(roleMenuId));
    }
}
