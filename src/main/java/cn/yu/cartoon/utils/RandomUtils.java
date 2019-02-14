package cn.yu.cartoon.utils;

import java.util.Random;

/**
 * 生成一些随机的数据
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/8 11:26
 **/
public class RandomUtils {

    /**
     *  用户需求生成指定长度的随机字符串
     *
     * @author Yu
     * @date 11:30 2019/2/8
     * @param length int 指定的长度
     * @return String 生成的随机字符串
     **/
    public static String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int num = random.nextInt(62);
            buf.append(str.charAt(num));
        }
        return buf.toString();
    }
}
