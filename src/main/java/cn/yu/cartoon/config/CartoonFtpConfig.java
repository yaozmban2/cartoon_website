package cn.yu.cartoon.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

/**
 * 读取cartoonFtp.properties文件中的配置
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/17 17:37
 **/
public class CartoonFtpConfig {

    private static Logger logger = LoggerFactory.getLogger(CartoonFtpConfig.class);

    public static String host;

    public static Integer port;

    public static String userName;

    public static String password;

    public static String basePath;

    public static String cartoonUrl;

    static {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile("classpath:properties/cartoonFtp.properties")));
            Properties p = new Properties();
            p.load(inputStream);
            host = p.getProperty("FTP.ADDRESS");
            port = new Integer(p.getProperty("FTP.PORT"));
            userName = p.getProperty("FTP.USERNAME");
            password = p.getProperty("FTP.PASSWORD");
            basePath = p.getProperty("FTP.BASEPATH");
            cartoonUrl = p.getProperty("IMAGE.BASE.URL");
        } catch (IOException e) {
            logger.error("读取配置文件失败", e);
        }
    }
}
