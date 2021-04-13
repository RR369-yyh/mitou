package com.mitou.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitou.user.response.Result;
import com.mitou.user.entity.BaseUser;
import com.mitou.user.entity.dto.BaseUserDto;
import com.mitou.user.entity.dto.BaseUserLoginDto;
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

    Result<BaseUserVo> selectByPrimaryKey(Long userId);

    Result<Page<BaseUserVo>> select(BaseUserQuery baseUserQuery, Integer pageNo, Integer pageSize);

    Result insert(BaseUserDto baseUserDto) ;

    Result updateByPrimaryKeySelective(BaseUser baseUser);

    Result deleteByPrimaryKey(Long userId);

    /**
     * 用户登录，根据主系统token
     *
     * @param mainToken 主系统token
     * @return
     */
    public Result<BaseUserLoginVo> loginByMainToken(String mainToken) ;

    /**
     * 用户登录
     *
     * @param baseUserLoginDto
     * @return
     */
    Result<BaseUserLoginVo> login(BaseUserLoginDto baseUserLoginDto) ;

    /**
     * 用户退出登录
     *
     * @return
     */
    Result logout();

    /**
     * 查询单位下的用户id
     * @param orgId
     * @return
     */
    List<BaseUser> selectByOrgId(Long orgId);
}
