package com.mitou.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mitou.user.entity.BaseRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统角色 Mapper 接口
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
public interface BaseRoleMapper extends BaseMapper<BaseRole> {

    /**
     * 查询用户的角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @Select("SELECT * FROM `base_user_role` rel INNER JOIN `base_role` role ON rel.ROLE_ID = role.ROLE_ID AND rel.USER_ID = #{userId}")
    public List<BaseRole> selectByUserId(@Param("userId") Long userId);

}
