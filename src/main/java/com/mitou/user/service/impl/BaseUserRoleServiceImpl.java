package com.mitou.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitou.user.entity.BaseUserRole;
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

}
