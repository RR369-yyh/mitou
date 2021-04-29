package com.mitou.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitou.common.response.Result;
import com.mitou.user.entity.BaseUser;
import com.mitou.user.entity.dto.BaseUserLoginDto;
import com.mitou.user.entity.dto.BaseUserSaveDto;
import com.mitou.user.entity.dto.BaseUserUpdatePwdDto;
import com.mitou.user.entity.query.BaseUserQuery;
import com.mitou.user.entity.vo.BaseUserLoginVo;
import com.mitou.user.entity.vo.BaseUserVo;

import java.util.List;

/**
 * <p>
 * 系统用户 服务接口类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
public interface IBaseUserService extends IService<BaseUser> {

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    BaseUserVo getById(Long userId);

    /**
     * 分页查询方法
     *
     * @param baseUserQuery
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<BaseUserVo> page(BaseUserQuery baseUserQuery, Integer pageNo, Integer pageSize);

    /**
     * 新增，注册用户
     *
     * @param saveDto
     * @return
     */
    boolean register(BaseUserSaveDto saveDto);

    /**
     * 检查电话的合法性，是否重复
     *
     * @param phone
     * @return true：合法；false：不合法
     */
    boolean checkPhoneLegal(String phone);

    /**
     * 删除数据，并清除掉与此用户的角色关联
     *
     * @param userIds
     * @return
     */
    boolean deleteByIds(List<Long> userIds);

    /**
     * 用户登录
     *
     * @param baseUserLoginDto
     * @return
     */
    Result<BaseUserLoginVo> login(BaseUserLoginDto baseUserLoginDto);

    /**
     * 用户退出登录
     *
     * @return
     */
    boolean logout();

    /**
     * 更新用户密码
     *
     * @param updatePwdDto
     * @return
     */
    Result<Boolean> updatePwd(BaseUserUpdatePwdDto updatePwdDto);

    /**
     * 查询单位下的用户id
     *
     * @param orgId
     * @return
     */
    List<BaseUser> getByOrgId(Long orgId);

}
