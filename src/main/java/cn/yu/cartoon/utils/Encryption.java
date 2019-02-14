package cn.yu.cartoon.utils;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 一些加密算法
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/8 10:47
 **/
@Component
public class Encryption {

    private static final String salt = "maimaiqian";

    /**
     *  加盐计算字符串MD5值
     *
     * @author Yu
     * @date 11:05 2019/2/8
     * @param string 需要加密的字符串
     * @return String 返回加密的字符串
     * @throws NoSuchAlgorithmException 没有md5算法的错误
     **/
    public static String md5(String string) throws NoSuchAlgorithmException {
        MessageDigest md5 = null;
        md5 = MessageDigest.getInstance("MD5");
        byte[] bytes = md5.digest((string + salt).getBytes());
        StringBuilder resultTemp = new StringBuilder();
        for (byte b : bytes) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            resultTemp.append(temp);
        }
        return resultTemp.toString();
    }
}
