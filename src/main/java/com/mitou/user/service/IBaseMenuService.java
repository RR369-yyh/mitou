package com.mitou.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitou.user.response.Result;
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

    Result<BaseMenu> selectByPrimaryKey(Long menuId);

    Result<Page<BaseMenu>> select(BaseMenuQuery baseMenuQuery, Integer pageNo, Integer pageSize);

    Result<List<BaseMenuTreeVo>> getTree();

    Result insert(BaseMenu baseMenu);

    Result updateByPrimaryKeySelective(BaseMenu baseMenu);

    Result deleteByPrimaryKey(Long menuId);

    /**
     * 查询当前用户拥有权限的菜单列表
     *
     * @return
     */
    Result<List<BaseMenuVo>> selectHas(BaseMenuHasQuery baseMenuHasQuery);

    /**
     * 为角色设置菜单，会覆盖之前的菜单
     *
     * @param baseRoleMenuDto 关系
     * @return
     */
    Result rel(BaseRoleMenuDto baseRoleMenuDto);
}
