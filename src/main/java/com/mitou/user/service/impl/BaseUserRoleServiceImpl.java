package com.mitou.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitou.user.response.Result;
import com.mitou.user.entity.BaseUserRole;
import com.mitou.user.entity.query.BaseUserRoleQuery;
import com.mitou.user.mapper.BaseUserRoleMapper;
import com.mitou.user.service.IBaseUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色关系 服务实现类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Service
public class BaseUserRoleServiceImpl extends ServiceImpl<BaseUserRoleMapper, BaseUserRole> implements IBaseUserRoleService {

    @Resource
    private BaseUserRoleMapper baseUserRoleMapper;

    @Override
    public Result<BaseUserRole> selectByPrimaryKey(Long userRoleId) {
        return Result.build(super.getById(userRoleId));
    }

    @Override
    public Result<Page<BaseUserRole>> select(BaseUserRoleQuery baseUserRoleQuery, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<BaseUserRole> lqw = new LambdaQueryWrapper<>();
        //进行条件组装

        //分页对象
        Page<BaseUserRole> page = new Page<>(pageNo, pageSize);
        super.page(page, lqw);
        return Result.build(page);
    }

    @Override
    public Result insert(BaseUserRole baseUserRole) {
        return Result.build(super.save(baseUserRole));
    }

    @Override
    public Result updateByPrimaryKeySelective(BaseUserRole baseUserRole) {
        return Result.build(super.updateById(baseUserRole));
    }

    @Override
    public Result deleteByPrimaryKey(Long userRoleId) {
        return Result.build(super.removeById(userRoleId));
    }
}
