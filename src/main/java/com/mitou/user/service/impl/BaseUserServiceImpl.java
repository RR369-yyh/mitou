package com.mitou.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitou.common.constants.BaseConstants;
import com.mitou.common.response.Result;
import com.mitou.common.response.ResultCode;
import com.mitou.common.utils.BaseUserUtil;
import com.mitou.common.utils.SecretKeyUtil;
import com.mitou.user.entity.BaseRole;
import com.mitou.user.entity.BaseUser;
import com.mitou.user.entity.BaseUserRole;
import com.mitou.user.entity.dto.BaseUserLoginDto;
import com.mitou.user.entity.dto.BaseUserSaveDto;
import com.mitou.user.entity.dto.BaseUserUpdatePwdDto;
import com.mitou.user.entity.query.BaseUserQuery;
import com.mitou.user.entity.vo.BaseUserLoginVo;
import com.mitou.user.entity.vo.BaseUserVo;
import com.mitou.user.mapper.BaseUserMapper;
import com.mitou.user.service.IBaseRoleService;
import com.mitou.user.service.IBaseUserRoleService;
import com.mitou.user.service.IBaseUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements IBaseUserService {

    @Resource
    private BaseUserMapper baseUserMapper;
    @Resource
    private BaseUserUtil baseUserUtil;
    @Resource
    private IBaseUserRoleService baseUserRoleService;
    @Resource
    private IBaseRoleService baseRoleService;

    @Override
    public BaseUserVo getById(Long userId) {
        BaseUserVo baseUserVo = null;
        BaseUser baseUser = super.getById(userId);
        if (null != baseUser) {
            baseUserVo = new BaseUserVo();
            BeanUtils.copyProperties(baseUser, baseUserVo);
            List<BaseRole> roleList = baseRoleService.getByUserId(baseUserVo.getUserId());
            baseUserVo.setRoleList(roleList);
        }
        return baseUserVo;
    }

    @Override
    public Page<BaseUserVo> page(BaseUserQuery baseUserQuery, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<BaseUser> lqw = new LambdaQueryWrapper<>();
        //进行条件组装
        //默认查询未删除的用户
        if (null == baseUserQuery.getDelFlag()) {
            baseUserQuery.setDelFlag(BaseConstants.DEL_FALSE);
        }
        lqw.eq(BaseUser::getDelFlag, baseUserQuery.getDelFlag());
        if (StringUtils.isNotBlank(baseUserQuery.getUserName())) {
            lqw.like(BaseUser::getUserName, baseUserQuery.getUserName());
        }
        if (null != baseUserQuery.getPhone()) {
            lqw.like(BaseUser::getPhone, baseUserQuery.getPhone());
        }
        //分页对象
        Page page = super.page(new Page<>(pageNo, pageSize), lqw);
        //组装角色信息
        if (!CollectionUtils.isEmpty(page.getRecords())) {
            List<BaseUserVo> userList = JSONObject.parseArray(JSON.toJSONString(page.getRecords()), BaseUserVo.class);
            page.setRecords(userList);
            for (BaseUserVo baseUserVo : userList) {
                List<BaseRole> roleList = baseRoleService.getByUserId(baseUserVo.getUserId());
                baseUserVo.setRoleList(roleList);
            }
        }
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(BaseUserSaveDto saveDto) {
        BaseUser baseUser = new BaseUser();
        BeanUtils.copyProperties(saveDto, baseUser);
        return this.register(baseUser, false);
    }

    @Override
    public boolean checkPhoneLegal(String phone) {
        boolean bool = false;
        BaseUser one = super.getOne(
                new LambdaQueryWrapper<BaseUser>()
                        .eq(BaseUser::getPhone, phone)
        );
        if (null == one) {
            bool = true;
        }
        return bool;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Long userId) {
        //清除掉与此用户的角色关联
        baseUserRoleService.remove(new LambdaQueryWrapper<BaseUserRole>().eq(BaseUserRole::getUserId, userId));
        return super.removeById(userId);
    }

    @Override
    public Result<BaseUserLoginVo> login(BaseUserLoginDto baseUserLoginDto) {
        LambdaQueryWrapper<BaseUser> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BaseUser::getPhone, baseUserLoginDto.getPhone());
        BaseUser one = super.getOne(lqw);
        if (null == one || !SecretKeyUtil.priDecrypt(one.getUserPwd()).equals(baseUserLoginDto.getUserPwd())) {
            return Result.failure(ResultCode.USER_LOGIN_ERROR);
        }
        String token = baseUserUtil.generateToken(one);
        //返回token与用户信息
        BaseUserLoginVo baseUserLoginVo = new BaseUserLoginVo();
        BeanUtils.copyProperties(one, baseUserLoginVo);
        baseUserLoginVo.setToken(token);
        return Result.success(baseUserLoginVo);
    }

    @Override
    public boolean logout() {
        return baseUserUtil.removeToken();
    }

    @Override
    public Result<Boolean> updatePwd(BaseUserUpdatePwdDto updatePwdDto) {
        Long userId = null == updatePwdDto.getUserId() ? baseUserUtil.getUserId() : updatePwdDto.getUserId();
        BaseUser byId = super.getById(userId);
        if (null == byId) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        }
        String userPwd = SecretKeyUtil.priDecrypt(byId.getUserPwd());
        if (!userPwd.equals(updatePwdDto.getOldUserPwd())) {
            return Result.failure(ResultCode.USER_PWD_ERROR);
        }
        userPwd = SecretKeyUtil.pubEncrypt(updatePwdDto.getUserPwd());
        BaseUser baseUser = new BaseUser();
        baseUser.setUserId(userId);
        baseUser.setUserPwd(userPwd);
        return Result.success(super.updateById(baseUser));
    }

    /**
     * 用户表注册，做用户注册时的一些初始化操作
     *
     * @param baseUser            用户信息
     * @param autoGiveDefaultRole 是否赋予默认角色
     * @return
     */
    private boolean register(BaseUser baseUser, boolean autoGiveDefaultRole) {
        baseUser.setCreateTime(new Date());
        String userPwd = baseUser.getUserPwd();
        if (StringUtils.isEmpty(userPwd)) {
            userPwd = BaseConstants.DEFAULT_PWD;
        }
        //密码加密
        baseUser.setUserPwd(SecretKeyUtil.pubEncrypt(userPwd));
        boolean save = super.save(baseUser);
        if (save) {
            if (autoGiveDefaultRole) {
                BaseRole baseRole = baseRoleService.getDefaultRole();
                if (null != baseRole) {
                    BaseUserRole baseUserRole = new BaseUserRole();
                    baseUserRole.setUserId(baseUser.getUserId());
                    baseUserRole.setRoleId(baseRole.getRoleId());
                    save = baseUserRoleService.save(baseUserRole);
                }
            }
        }
        return save;
    }

    @Override
    public List<BaseUser> getByOrgId(Long orgId) {
        LambdaQueryWrapper<BaseUser> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BaseUser::getOrgId, orgId);
        return super.list(lqw);
    }

}
