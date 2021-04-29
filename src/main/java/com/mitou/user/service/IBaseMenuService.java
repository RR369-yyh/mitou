package com.mitou.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitou.common.response.Result;
import com.mitou.user.entity.BaseMenu;
import com.mitou.user.entity.dto.BaseRoleMenuDto;
import com.mitou.user.entity.query.BaseMenuHasQuery;
import com.mitou.user.entity.query.BaseMenuQuery;
import com.mitou.user.entity.vo.BaseMenuTreeVo;
import com.mitou.user.entity.vo.BaseMenuVo;

import java.util.List;

/**
 * <p>
 * 菜单 服务接口类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
public interface IBaseMenuService extends IService<BaseMenu> {


    /**
     * 分页查询方法
     *
     * @param baseMenuQuery
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<BaseMenu> page(BaseMenuQuery baseMenuQuery, Integer pageNo, Integer pageSize);

    /**
     * 查询树列表
     *
     * @return
     */
    List<BaseMenuTreeVo> getTree();

    /**
     * 删除数据，并清除掉与此菜单的角色关联
     *
     * @param menuIds
     * @return
     */
    boolean deleteByIds(List<Long> menuIds);

    /**
     * 查询当前用户拥有权限的菜单列表
     *
     * @param baseMenuHasQuery
     * @return
     */
    List<BaseMenuVo> selectHas(BaseMenuHasQuery baseMenuHasQuery);

    /**
     * 为角色设置菜单，会覆盖之前的菜单
     *
     * @param baseRoleMenuDto 关系
     * @return
     */
    boolean rel(BaseRoleMenuDto baseRoleMenuDto);
}
