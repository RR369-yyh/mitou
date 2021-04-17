package com.mitou.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitou.user.entity.BaseRoleMenu;
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

}
