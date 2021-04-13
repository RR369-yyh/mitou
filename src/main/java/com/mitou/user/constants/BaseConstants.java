package com.mitou.user.constants;

/**
 * <p>
 * 系统常量类
 * <p/>
 *
 * @author rice
 * @since 2021-03-25
 */
public class BaseConstants {

    /**
     * 与前端约定的存放于header中的token名
     */
    public static final String TOKEN_MAME = "Authorization";

    /**
     * 系统中默认顶级父id
     */
    public static final Long DEFAULT_PARENT_ID = 0L;

    /**
     * 系统中默认密码
     */
    public static final String DEFAULT_PWD = "mitou123";

    /**
     * 删除状态：已删除
     */
    public static final Integer DEL_TRUE = 0;
    /**
     * 删除状态：正常/未删除
     */
    public static final Integer DEL_FALSE = 1;

    /*以下为系统中业务常量***********************************************************/
    /**
     * 菜单类型：菜单
     */
    public static final Integer BASE_MENU_1 = 1;

    /**
     * 是否为默认角色：是
     */
    public static final Integer DEFAULT_ROLE_TRUE = 1;
}
