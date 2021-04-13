package com.mitou.user.auth;

import java.lang.annotation.*;

/**
 * <p>
 * 权限校验
 * <p/>
 *
 * @author rice
 * @since 2021-03-26
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoleAuth {
    String value() default "";
}
