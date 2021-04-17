package com.mitou.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitou.common.constants.BaseConstants;
import com.mitou.common.utils.BaseUserUtil;
import com.mitou.user.entity.BaseRole;
import com.mitou.user.entity.BaseRoleMenu;
import com.mitou.user.entity.BaseUserRole;
import com.mitou.user.entity.dto.BaseUserRoleDto;
import com.mitou.user.entity.query.BaseRoleQuery;
import com.mitou.user.mapper.BaseRoleMapper;
import com.mitou.user.service.IBaseRoleMenuService;
import com.mitou.user.service.IBaseRoleService;
import com.mitou.user.service.IBaseUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Service
public class BaseRoleServiceImpl extends ServiceImpl<BaseRoleMapper, BaseRole> implements IBaseRoleService {

    @Resource
    private BaseRoleMapper baseRoleMapper;
    @Resource
    private IBaseUserRoleService baseUserRoleService;
    @Resource
    private BaseUserUtil baseUserUtil;
    @Resource
    private IBaseRoleMenuService baseRoleMenuService;


    @Override
    public Page<BaseRole> page(BaseRoleQuery baseRoleQuery, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<BaseRole> lqw = new LambdaQueryWrapper<>();
        //进行条件组装
        //默认查询未删除的角色
        if (null == baseRoleQuery.getDelFlag()) {
            baseRoleQuery.setDelFlag(BaseConstants.DEL_FALSE);
        }
        lqw.eq(BaseRole::getDelFlag, baseRoleQuery.getDelFlag());
        if (StringUtils.isNotBlank(baseRoleQuery.getRoleName())) {
            lqw.like(BaseRole::getRoleName, baseRoleQuery.getRoleName());
        }
        if (null != baseRoleQuery.getSysInit()) {
            lqw.eq(BaseRole::getSysInit, baseRoleQuery.getSysInit());
        }
        //分页对象
        Page<BaseRole> page = new Page<>(pageNo, pageSize);
        return super.page(page, lqw);
    }

    @Override
    public List<BaseRole> getByUserId(Long userId) {
        if (null == userId) {
            userId = baseUserUtil.getUserId();
        }
        List<BaseRole> roleList = baseRoleMapper.getByUserId(userId);
        if (null == roleList) {
            roleList = new ArrayList<>();
        }
        return roleList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Long roleId) {
        //清除掉与此角色的菜单关联
        baseRoleMenuService.remove(new LambdaQueryWrapper<BaseRoleMenu>().eq(BaseRoleMenu::getRoleId, roleId));
        //清除掉与此角色的用户关联
        baseUserRoleService.remove(new LambdaQueryWrapper<BaseUserRole>().eq(BaseUserRole::getRoleId, roleId));
        return super.removeById(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean rel(BaseUserRoleDto baseUserRoleDto) {
        Long userId = baseUserRoleDto.getUserId();
        List<Long> roleIdList = baseUserRoleDto.getRoleIdList();
        List<BaseUserRole> relList = new ArrayList<>();
        for (Long roleId : roleIdList) {
            BaseUserRole baseUserRole = new BaseUserRole();
            baseUserRole.setUserId(userId);
            baseUserRole.setRoleId(roleId);
            relList.add(baseUserRole);
        }
        //清除掉之前的角色
        baseUserRoleService.remove(new LambdaQueryWrapper<BaseUserRole>().eq(BaseUserRole::getUserId, userId));
        return baseUserRoleService.saveBatch(relList);
    }

    @Override
    public BaseRole getDefaultRole() {
        BaseRole baseRole = null;
        LambdaQueryWrapper<BaseRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BaseRole::getRoleDefault, BaseConstants.DEFAULT_ROLE_TRUE);
        List<BaseRole> list = super.list(lqw);
        if (!CollectionUtils.isEmpty(list)) {
            baseRole = list.get(0);
        }
        return baseRole;
    }
}
