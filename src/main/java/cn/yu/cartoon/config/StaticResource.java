package cn.yu.cartoon.config;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 一些多线程要使用的静态资源
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/4 15:17
 **/
public class StaticResource {

    /**
     *  使用的时间格式
     */
    private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static String formatStr = "yyyy-MM-dd HH:mm:ss";

    public static DateTimeFormatter getFormat() {
        return format;
    }

    public static String getFormatStr() {
        return formatStr;
    }
}
