package cn.yu.cartoon.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

/**
 * 一个用来存放本系统的日常运行配置
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/28 17:31
 **/
public class CartoonSystemConfig {

    private static Logger logger = LoggerFactory.getLogger(CartoonSystemConfig.class);

    private static CartoonSystemConfig cartoonSystemConfig;

    /**
     * 30天vip的价格
     */
    private int vipPrice;
    /**
     *  金币的价格 1rmb能兑换多少金币
     */
    private int goldCoinPrice;
    /**
     * 图片服务器的地址
     */
    private String pictureSitePath;

    /**
     * 负责接收支付宝通知的NIO线程的监听端口
     */
    private int listenPayInfoPort;

    private static class CartoonSystemConfigHolder {
        private static CartoonSystemConfig cartoonSystemConfig = new CartoonSystemConfig();

        static {
            InputStream inputStream = null;
            try {
                //classpath:为resource文件夹
                inputStream = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile("classpath:properties/cartoonSystemConfig.properties")));
                Properties p = new Properties();
                p.load(inputStream);
                cartoonSystemConfig.vipPrice = Integer.valueOf(p.getProperty("vipPrice"));
                cartoonSystemConfig.goldCoinPrice = Integer.valueOf(p.getProperty("goldCoinPrice"));
                cartoonSystemConfig.pictureSitePath = p.getProperty("pictureSitePath");
                cartoonSystemConfig.listenPayInfoPort = Integer.valueOf(p.getProperty("listenPayInfoPort"));
            } catch (FileNotFoundException e) {
                logger.warn("没有cartoonSystemConfig.properties文件", e);
            } catch (IOException e) {
                logger.warn("第45行出错", e);
            }
        }
    }

    private CartoonSystemConfig() {

    }

    public static CartoonSystemConfig getCartoonSystemConfig() {
        return CartoonSystemConfigHolder.cartoonSystemConfig;
    }

    public int getVipPrice() {
        return vipPrice;
    }

    public int getGoldCoinPrice() {
        return goldCoinPrice;
    }

    public String getPictureSitePath() {
        return pictureSitePath;
    }

    public int getListenPayInfoPort() {
        return listenPayInfoPort;
    }
}
