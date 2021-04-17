package com.mitou.common.utils;

import com.mitou.common.exception.UserNotLoginException;
import com.mitou.common.constants.BaseConstants;
import com.mitou.user.entity.BaseUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 与用户相关的信息
 *
 * @author rice
 * @since 2021-03-25
 */
@Component
public class BaseUserUtil {

    @Resource
    private RedisCacheUtil redisCacheUtil;

    /**
     * 获取当前登录用户id
     *
     * @return
     */
    public Long getUserId() {
        try {
            String token = RequestContextUtil.getRequest().getHeader(BaseConstants.TOKEN_MAME);
            return Long.parseLong(redisCacheUtil.getStr(token));
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserNotLoginException("用户未登录，请检查token信息！");
        }
    }

    /**
     * 生成token
     *
     * @param one 用户信息
     * @return
     */
    public String generateToken(BaseUser one) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //定义加密值
            String salt = one.getUserId() + one.getUserPwd();
            //加密生成token
            String token = Base64Utils.encodeToString(
                    md5.digest(salt.getBytes(StandardCharsets.UTF_8))
            ) + new Random().nextInt(100);
            //把token放入redis，保存30分钟
            redisCacheUtil.setStr(token, one.getUserId().toString(), 30, TimeUnit.MINUTES);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("服务器加密失败，请稍后重试！");
        }
    }

    /**
     * 清除当前登录用户token
     *
     * @return
     */
    public boolean removeToken() {
        boolean bool = false;
        try {
            String token = RequestContextUtil.getRequest().getHeader(BaseConstants.TOKEN_MAME);
            bool = redisCacheUtil.deleteStr(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }
}
