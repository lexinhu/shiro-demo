package com.xn2001.tools;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 乐心湖
 * @date 2021/2/8 18:57
 **/
public class DigestsUtil {

    // 加密形式
    private static final String SHA1 = "SHA-1";
    // 加密次数
    private static final Integer ITERATIONS = 512;

    /**
     * @param input 需要散列字符串
     * @param salt  盐字符串
     */
    public static String sha1(String input, String salt) {
        /*
         * algorithmName：加密形式
         * source：原始明文密码
         * salt：盐值
         * hashIterations：加密次数
         */
        return new SimpleHash(SHA1, input, salt, ITERATIONS).toString();
    }

    /**
     * 随机获得 salt 盐字符串
     */
    public static String generateSalt() {
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        return randomNumberGenerator.nextBytes().toHex();
    }

    /**
     * 生成密码字符密文和 salt
     */
    public static Map<String, String> encryptPassword(String passwordPlain) {
        Map<String, String> map = new HashMap<>();
        String salt = generateSalt();
        String password = sha1(passwordPlain, salt);
        map.put("salt", salt);
        map.put("password", password);
        return map;
    }
}
