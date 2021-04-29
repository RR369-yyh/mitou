package com.mitou.common.utils;

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

    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbmwhPYkMcmM+Vst8VTqjBPUYHTDEQQvPPJ+rI28OsCseojMZy/bzRMz7UHfD8CS0WQ0lvRvxUKt1h2II/6MaMCAwEAAQ==";
    private static final String PRIVATE_KEY = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAhubCE9iQxyYz5Wy3xVOqME9RgdMMRBC888n6sjbw6wKx6iMxnL9vNEzPtQd8PwJLRZDSW9G/FQq3WHYgj/oxowIDAQABAkA4nT919L1N1sqQzj+RoLsb6y3Zvii3qA6kgW+ku0oAVlRXi6gsjaynXMG8SyATFALDeuI/kRUqYUrBCBuZE3kBAiEAzqkafDp1QvA48lLt/LK4Ye66VOi4zblRCMiExbm501kCIQCnG82WoRTFwm0iZkx2xzl5ub7CAZYkx3xtKcEiiF95WwIgELQISPwsmCF0aNmdFKyZTIkQFGbO8QnGa/BREu4k/9kCIDwNwdRgDxcCyDWUjeYhsoYMOoF+EHcnOZABcWb3m1gBAiEAwiWAnzyB9jOBImw4KOBYv/ye/NEW7aBtx0iWt8+y/9s=";
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
     * 公钥加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    private static String encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空,请设置");
        }
        try {
            Cipher cipher = Cipher.getInstance(SIGN_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return Base64.encodeBase64String(output);
        } catch (NoSuchAlgorithmException e) {
            log.error("无此加密算法", e);
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
            return null;
        } catch (InvalidKeyException e) {
            log.error("加密公钥非法,请检查", e);
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            log.error("明文长度非法", e);
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            log.error("明文数据已损坏", e);
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    private static String decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        try {
            Cipher cipher = Cipher.getInstance(SIGN_NAME);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return new String(output);
        } catch (NoSuchAlgorithmException e) {
            log.error("无此解密算法", e);
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
            return null;
        } catch (InvalidKeyException e) {
            log.error("解密私钥非法,请检查", e);
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            log.error("密文长度非法", e);
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            log.error("密文数据已损坏", e);
            throw new Exception("密文数据已损坏");
        }
    }


    /**
     * 公钥加密
     *
     * @param content 明文
     * @return 密文
     */
    public static String pubEncrypt(String content) {
        try {
            //加载公钥
            RSAPublicKey rsaPubKey = loadPublicKeyByStr();
            //公钥加密
            return encrypt(rsaPubKey, content.getBytes());
        } catch (Exception e) {
            log.error("公钥加密出错,请稍后重试!", e);
            throw new RuntimeException("公钥加密出错,请稍后重试!");
        }
    }

    /**
     * 私钥解密
     *
     * @param content 加密后的密文
     * @return 明文
     */
    public static String priDecrypt(String content) {
        try {
            //加载私钥
            RSAPrivateKey rsaPriKey = loadPrivateKeyByStr();
            //解密
            return decrypt(rsaPriKey, Base64.decodeBase64(content));
        } catch (Exception e) {
            log.error("私钥解密出错,请稍后重试!", e);
            throw new RuntimeException("私钥解密出错,请稍后重试!");
        }
    }

}
