package com.mitou.user.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * RSA加解密工具类
 *
 * @author rice
 * @since 2021-03-25
 */
public class SecretKeyUtil {
    private static final Logger log = LoggerFactory.getLogger(SecretKeyUtil.class);

    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOnFgy1F2a3bP4a8TYTTr+lncFhWjQ+cW/0n88KCatN/+AyjujI30CB+0bNOc5cUdXemP5jvsrQ2iZMCsryPteUCAwEAAQ==";
    private static final String PRIVATE_KEY = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEA6cWDLUXZrds/hrxNhNOv6WdwWFaND5xb/SfzwoJq03/4DKO6MjfQIH7Rs05zlxR1d6Y/mO+ytDaJkwKyvI+15QIDAQABAkAini4bwTFIDqSspivwlIyDSt8XJdID0srIhWcSkc+R/CPfSJgIuEp6MIO65c9BFKG6gCK3bIqNP8SdmGNO4EmBAiEA+Q+om+FZZraqiw20iBe7JJDIbqAu+DMHq7c0TtPj62ECIQDwSM5LJoX16srO7pNLhE+dKFcz7YJKxbSAfyqBXcY9BQIgMeAU38Js6MjDtjz0XhyCeXwU5zJktYdijdyOOQrtpYECICwtJohYh86DoU/UOw5qP/zj2sx4QTkgCiSJvLXWGMlFAiEA3X++YyJDeSukj8HbWvnWR/ihqSJMqaY8F9pp62etbJc=";
    private static final String SIGN_NAME = "RSA";

    /**
     * 从字符串中加载公钥
     *
     * @throws Exception 加载公钥时产生的异常
     */
    private static RSAPublicKey loadPublicKeyByStr() throws Exception {
        try {
            byte[] buffer = Base64.decodeBase64(PUBLIC_KEY);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(SIGN_NAME);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("无此算法", e);
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            log.error("公钥非法", e);
            throw new Exception("公钥非法");
        } catch (NullPointerException e) {
            log.error("公钥数据为空", e);
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从字符串中加载私钥
     *
     * @throws Exception 加载公钥时产生的异常
     */
    private static RSAPrivateKey loadPrivateKeyByStr() throws Exception {
        try {
            byte[] buffer = Base64.decodeBase64(PRIVATE_KEY);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance(SIGN_NAME);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            log.error("无此算法", e);
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            log.error("私钥非法", e);
            throw new Exception("私钥非法");
        } catch (NullPointerException e) {
            log.error("私钥数据为空", e);
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 私钥加密过程
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    private static String encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空,请设置");
        }
        try {
            Cipher cipher = Cipher.getInstance(SIGN_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(plainTextData);
            return Base64.encodeBase64String(output);
        } catch (NoSuchAlgorithmException e) {
            log.error("无此加密算法", e);
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
            return null;
        } catch (InvalidKeyException e) {
            log.error("加密私钥非法,请检查", e);
            throw new Exception("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            log.error("明文长度非法", e);
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            log.error("明文长度非法", e);
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 公钥解密过程
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    private static String decrypt(RSAPublicKey publicKey, byte[] cipherData) throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空,请设置");
        }
        try {
            Cipher cipher = Cipher.getInstance(SIGN_NAME);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(cipherData);
            return new String(output);
        } catch (NoSuchAlgorithmException e) {
            log.error("无此解密算法", e);
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
            return null;
        } catch (InvalidKeyException e) {
            log.error("解密公钥非法,请检查", e);
            throw new Exception("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            log.error("密文长度非法", e);
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            log.error("密文数据已损坏", e);
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 私钥加密
     *
     * @param content 明文
     * @return 密文
     */
    public static String priEncrypt(String content) {
        try {
            //加载私钥
            RSAPrivateKey rsaPriKey = loadPrivateKeyByStr();
            //私钥加密
            return encrypt(rsaPriKey, content.getBytes());
        } catch (Exception e) {
            log.error("私钥加密出错,请稍后重试!", e);
            throw new RuntimeException("私钥加密出错,请稍后重试!");
        }
    }

    /**
     * 公钥解密
     *
     * @param content 加密后的密文
     * @return 明文
     */
    public static String pubDecrypt(String content) {
        try {
            //加载公钥
            RSAPublicKey rsaPubKey = loadPublicKeyByStr();
            //解密
            return decrypt(rsaPubKey, Base64.decodeBase64(content));
        } catch (Exception e) {
            log.error("公钥解密出错,请稍后重试!", e);
            throw new RuntimeException("公钥解密出错,请稍后重试!");
        }
    }

}
