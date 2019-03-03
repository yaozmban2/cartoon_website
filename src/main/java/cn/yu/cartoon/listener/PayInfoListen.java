package cn.yu.cartoon.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/3 14:53
 **/
@Component
public class PayInfoListen implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(InitializingBean.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.debug("开始启动支付宝通知监听线程！");

        logger.debug("支付宝通知监听线程启动完毕！");
    }
}
