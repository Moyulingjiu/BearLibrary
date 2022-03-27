package com.bear.encript;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES加密类
 *
 * @author moyulingjiu
 * create 2022年3月27日
 */
public class Aes {
    private static final Logger logger = LoggerFactory.getLogger(Aes.class);

    /**
     * 秘钥长度
     * <br/>
     * <span style="color:red"><b>如无必要请不要更改该长度，请确保数据库中不会有因为修改导致无法解密的字段！</b></span>
     */
    private static final int KEY_SIZE = 128;

    /**
     * 加密
     *
     * @param content 明文
     * @param secret  初始秘钥（程序会根据该字符串生成实际的秘钥）
     * @return 密文
     */
    public static String encrypt(String content, String secret) {
        if (null == content) {
            return null;
        }

        try {
            SecretKeySpec key = getSecretKey(secret);
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            return parseByte2HexStr(result);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e.toString());
        }
        return null;
    }

    /**
     * 解密AES加密过的字符串
     *
     * @param content 密文
     * @param secret  加密时的密码
     * @return 明文
     */
    public static String decrypt(String content, String secret) {
        if (content == null) {
            return null;
        }

        try {
            // 创建AES的Key生产者
            SecretKeySpec key = getSecretKey(secret);
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] byteContent = parseHexStr2Byte(content);
            if (byteContent == null) {
                return null;
            }
            byte[] result = cipher.doFinal(byteContent);
            // 明文
            return new String(result);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e.toString());
        }
        return null;
    }

    /**
     * 生成密钥
     *
     * @param password 输入密钥
     * @return 输出密钥
     * @throws NoSuchAlgorithmException 异常
     */
    private static SecretKeySpec getSecretKey(String password) throws NoSuchAlgorithmException {
        // 创建AES的Key生产者
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        //利用用户密码作为随机数初始化出128位的key生产者
        //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        generator.init(KEY_SIZE, secureRandom);
        // 根据用户密码，生成一个密钥
        SecretKey secretKey = generator.generateKey();
        // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
        byte[] enCodeFormat = secretKey.getEncoded();
        // 转换为AES专用密钥
        return new SecretKeySpec(enCodeFormat, "AES");
    }

    /**
     * 将byte数组转换成16进制字符串
     *
     * @param buffer byte数组
     * @return 16进制字符串
     */
    public static String parseByte2HexStr(byte[] buffer) {
        StringBuilder builder = new StringBuilder();
        for (byte b : buffer) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            builder.append(hex.toUpperCase());
        }
        return builder.toString();
    }

    /**
     * 将16进制字符串转换为byte数组
     *
     * @param hexStr 16进制字符串
     * @return byte数组
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        // 单个byte转为2位的16进制数
        int size = 2;
        for (int i = 0; i < hexStr.length() / size; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
