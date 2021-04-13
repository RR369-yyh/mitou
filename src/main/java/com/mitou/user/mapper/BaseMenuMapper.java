package com.mitou.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mitou.user.entity.BaseMenu;
import com.mitou.user.entity.vo.BaseMenuVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author rice
 * @since 2021-03-25
 */
public interface BaseMenuMapper extends BaseMapper<BaseMenu> {

    /**
     * 查询当前用户所拥有的菜单列表
     *
     * @param userId       用户id
     * @param menuType     菜单类型
     * @param parentMenuId 菜单父id
     * @return 列表集合
     */
    @Select("<script>" +
            "SELECT menu.* FROM" +
            " ( SELECT DISTINCT base_role_menu.MENU_ID FROM `base_user_role` LEFT JOIN `base_role_menu`" +
            "    ON base_user_role.ROLE_ID = base_role_menu.ROLE_ID AND USER_ID = #{userId}" +
            " ) as rel " +
            "INNER JOIN `base_menu` menu ON menu.MENU_ID = rel.MENU_ID" +
            " AND menu.DEL_FLAG = 1" +
            "<when test= 'menuType!=null'> AND menu.MENU_TYPE = #{menuType} </when>"+
            "<when test= 'parentMenuId!=null'> AND menu.PARENT_MENU_ID = #{parentMenuId} </when>" +
            " ORDER BY menu.ORDER_NUM,menu.CREATE_TIME DESC" +
            "</script>")
    public List<BaseMenuVo> selectHas(@Param("userId") Long userId, @Param("menuType") Integer menuType, @Param("parentMenuId") Long parentMenuId);
}
