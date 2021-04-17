package com.mitou.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitou.common.constants.BaseConstants;
import com.mitou.common.response.Result;
import com.mitou.common.utils.BaseUserUtil;
import com.mitou.common.utils.TreeUtils;
import com.mitou.user.entity.BaseMenu;
import com.mitou.user.entity.BaseRoleMenu;
import com.mitou.user.entity.dto.BaseRoleMenuDto;
import com.mitou.user.entity.query.BaseMenuHasQuery;
import com.mitou.user.entity.query.BaseMenuQuery;
import com.mitou.user.entity.vo.BaseMenuTreeVo;
import com.mitou.user.entity.vo.BaseMenuVo;
import com.mitou.user.mapper.BaseMenuMapper;
import com.mitou.user.service.IBaseMenuService;
import com.mitou.user.service.IBaseRoleMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
@Service
public class BaseMenuServiceImpl extends ServiceImpl<BaseMenuMapper, BaseMenu> implements IBaseMenuService {

    @Resource
    private BaseMenuMapper baseMenuMapper;
    @Resource
    private BaseUserUtil baseUserUtil;
    @Resource
    private IBaseRoleMenuService baseRoleMenuService;

    @Override
    public Page<BaseMenu> page(BaseMenuQuery baseMenuQuery, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<BaseMenu> lqw = new LambdaQueryWrapper<>();
        //进行条件组装
        //默认查询未删除的菜单
        if (null == baseMenuQuery.getDelFlag()) {
            baseMenuQuery.setDelFlag(BaseConstants.DEL_FALSE);
        }
        lqw.eq(BaseMenu::getDelFlag, baseMenuQuery.getDelFlag());
        if (StringUtils.isNotBlank(baseMenuQuery.getMenuName())) {
            lqw.like(BaseMenu::getMenuName, baseMenuQuery.getMenuName());
        }
        if (null != baseMenuQuery.getMenuType()) {
            lqw.eq(BaseMenu::getMenuType, baseMenuQuery.getMenuType());
        }
        //分页对象
        Page<BaseMenu> page = new Page<>(pageNo, pageSize);
        return super.page(page, lqw);
    }

    @Override
    public List<BaseMenuTreeVo> getTree() {
        List<BaseMenuTreeVo> tree = new ArrayList<>();
        try {
            List<BaseMenu> list = super.list(
                    new LambdaQueryWrapper<BaseMenu>().eq(BaseMenu::getDelFlag, BaseConstants.DEL_FALSE));
            List<BaseMenuTreeVo> listVo = JSONObject.parseArray(JSON.toJSONString(list), BaseMenuTreeVo.class);
            tree = TreeUtils.getTree(listVo, "menuId", "parentMenuId",
                    0L, "childrenList");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tree;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Long menuId) {
        //清除掉与此菜单的角色关联
        baseRoleMenuService.remove(new LambdaQueryWrapper<BaseRoleMenu>().eq(BaseRoleMenu::getMenuId, menuId));
        return super.removeById(menuId);
    }

    @Override
    public List<BaseMenuVo> selectHas(BaseMenuHasQuery baseMenuHasQuery) {
        Long parentId = BaseConstants.DEFAULT_PARENT_ID;
        Integer menuId = BaseConstants.BASE_MENU_1;
        //默认查询顶级的、菜单类型的清单
        if (null != baseMenuHasQuery) {
            if (null != baseMenuHasQuery.getParentMenuId()) {
                parentId = baseMenuHasQuery.getParentMenuId();
            }
            if (null != baseMenuHasQuery.getMenuType()) {
                menuId = baseMenuHasQuery.getMenuType();
            }
        }
        List<BaseMenuVo> baseMenuVoList = baseMenuMapper.selectHas(baseUserUtil.getUserId(), menuId, parentId);
        if (null == baseMenuVoList) {
            baseMenuVoList = new ArrayList<>();
        }
        return baseMenuVoList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean rel(BaseRoleMenuDto baseRoleMenuDto) {
        Long roleId = baseRoleMenuDto.getRoleId();
        List<Long> menuIdList = baseRoleMenuDto.getMenuIdList();
        List<BaseRoleMenu> relList = new ArrayList<>();
        for (Long menuId : menuIdList) {
            BaseRoleMenu baseRoleMenu = new BaseRoleMenu();
            baseRoleMenu.setRoleId(roleId);
            baseRoleMenu.setMenuId(menuId);
            relList.add(baseRoleMenu);
        }
        //清除掉之前的菜单
        baseRoleMenuService.remove(new LambdaQueryWrapper<BaseRoleMenu>().eq(BaseRoleMenu::getRoleId, roleId));
        return baseRoleMenuService.saveBatch(relList);
    }
}
