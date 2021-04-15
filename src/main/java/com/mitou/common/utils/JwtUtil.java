package com.mitou.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * 解析主系统jwt加密串，暂放于此
 *
 * @author rice
 * @since 2021-03-25
 */
public class JwtUtil {

    /**
     * 公钥
     */
    private static String publicKey = "a8@37y52k66j1";


    /**
     * 获取body某个值
     *
     * @param token
     * @param key
     * @return
     */
    public static Object getVal(String token, String key) {
        try {
            return getClaimsBody(token).get(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取body
     *
     * @param token
     * @return
     */
    public static Claims getClaimsBody(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }

}
