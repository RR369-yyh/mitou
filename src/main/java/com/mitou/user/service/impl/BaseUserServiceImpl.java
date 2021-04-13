package com.mitou.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitou.user.response.Result;
import com.mitou.user.utils.BaseUserUtil;
import com.mitou.user.utils.JwtUtil;
import com.mitou.user.utils.SecretKeyUtil;
import com.mitou.user.constants.BaseConstants;
import com.mitou.user.entity.BaseRole;
import com.mitou.user.entity.BaseUser;
import com.mitou.user.entity.BaseUserRole;
import com.mitou.user.entity.dto.BaseUserDto;
import com.mitou.user.entity.dto.BaseUserLoginDto;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public Result<BaseUserVo> selectByPrimaryKey(Long userId) {
        BaseUserVo baseUserVo = null;
        BaseUser baseUser = super.getById(userId);
        if (null != baseUser) {
            baseUserVo = new BaseUserVo();
            BeanUtils.copyProperties(baseUser, baseUserVo);
            Result<List<BaseRole>> listResult = baseRoleService.selectByUserId(baseUserVo.getUserId());
            List<BaseRole> roleList = listResult.getData();
            baseUserVo.setRoleList(roleList);
        }
        return Result.build(baseUserVo);
    }

    @Override
    public Result<Page<BaseUserVo>> select(BaseUserQuery baseUserQuery, Integer pageNo, Integer pageSize) {
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
                Result<List<BaseRole>> listResult = baseRoleService.selectByUserId(baseUserVo.getUserId());
                List<BaseRole> roleList = listResult.getData();
                baseUserVo.setRoleList(roleList);
            }
        }
        return Result.build(page);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result insert(BaseUserDto baseUserDto)  {
        BaseUser baseUser = new BaseUser();
        BeanUtils.copyProperties(baseUserDto, baseUser);
        boolean save = this.register(baseUser, false);
        if (save) {
            //如果有角色，则保存与角色之间的关系
            List<Long> roleIdList = baseUserDto.getRoleIdList();
            if (!CollectionUtils.isEmpty(roleIdList)) {
                List<BaseUserRole> relList = new ArrayList<>();
                for (Long roleId : roleIdList) {
                    BaseUserRole baseUserRole = new BaseUserRole();
                    baseUserRole.setUserId(baseUser.getUserId());
                    baseUserRole.setRoleId(roleId);
                    relList.add(baseUserRole);
                }
                save = baseUserRoleService.saveBatch(relList);
            }
        }
        return Result.build(save);
    }

    @Override
    public Result updateByPrimaryKeySelective(BaseUser baseUser) {
        return Result.build(super.updateById(baseUser));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result deleteByPrimaryKey(Long userId) {
        //清除掉与此用户的角色关联
        baseUserRoleService.remove(new LambdaQueryWrapper<BaseUserRole>().eq(BaseUserRole::getUserId, userId));
        return Result.build(super.removeById(userId));
    }

    @Override
    public Result<BaseUserLoginVo> loginByMainToken(String mainToken) {
        Map<String, String> map = (Map) JwtUtil.getVal(mainToken, "sub");
        if (null == map) {
            return Result.fail.error("登录失败，请检查token信息！");
        }
        LambdaQueryWrapper<BaseUser> lqw = new LambdaQueryWrapper<>();
        String phone = map.get("phone");
        lqw.eq(BaseUser::getPhone, phone);
        BaseUser one = super.getOne(lqw);
        if (null == one) {
            BaseUser baseUser = new BaseUser();
            baseUser.setMainUserId(map.get("userId"));
            baseUser.setUserName(map.get("realName"));
            baseUser.setPhone(map.get("phone"));
            baseUser.setOrgName(map.get("orgName"));
            baseUser.setDept(map.get("dept"));
            baseUser.setUserType(map.get("userType"));
            baseUser.setPosition(map.get("position"));
            baseUser.setGender("男".equals(map.get("gender")) ? 0 : 1);
            baseUser.setEmail(map.get("dept"));
            this.register(baseUser, true);
            one = super.getOne(lqw);
        }
        String token = baseUserUtil.generateToken(one);
        //返回token与用户信息
        BaseUserLoginVo baseUserLoginVo = new BaseUserLoginVo();
        BeanUtils.copyProperties(one, baseUserLoginVo);
        baseUserLoginVo.setToken(token);
        return Result.build(baseUserLoginVo);
    }

    @Override
    public Result<BaseUserLoginVo> login(BaseUserLoginDto baseUserLoginDto) {
        LambdaQueryWrapper<BaseUser> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BaseUser::getPhone, baseUserLoginDto.getPhone());
        lqw.eq(BaseUser::getUserPwd, SecretKeyUtil.priEncrypt(baseUserLoginDto.getUserPwd()));
        BaseUser one = super.getOne(lqw);
        if (null == one) {
            return Result.fail.error("登录失败，请检查用户名或密码！");
        }
        String token = baseUserUtil.generateToken(one);
        //返回token与用户信息
        BaseUserLoginVo baseUserLoginVo = new BaseUserLoginVo();
        BeanUtils.copyProperties(one, baseUserLoginVo);
        baseUserLoginVo.setToken(token);
        return Result.build(baseUserLoginVo);
    }

    @Override
    public Result logout() {
        baseUserUtil.removeToken();
        return Result.success;
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
        baseUser.setUserPwd(SecretKeyUtil.priEncrypt(userPwd));
        boolean save = super.save(baseUser);
        if (save) {
            if (autoGiveDefaultRole) {
                Result<BaseRole> defaultRole = baseRoleService.getDefaultRole();
                BaseRole baseRole = defaultRole.getData();
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
    public List<BaseUser> selectByOrgId(Long orgId) {
        LambdaQueryWrapper<BaseUser> lqw = new LambdaQueryWrapper<>();
        lqw.eq(BaseUser::getOrgId, orgId);
        return super.list(lqw);
    }
}