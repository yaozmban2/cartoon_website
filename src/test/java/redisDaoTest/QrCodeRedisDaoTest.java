package redisDaoTest;

import cn.yu.cartoon.redis.QrCodeRedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/3 23:00
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class QrCodeRedisDaoTest {

    @Autowired
    QrCodeRedisDao qrCodeRedisDao;


    @Test
    public void insertQrCodeWithComboTest() {
        System.out.println(new BigDecimal(9.90));
        qrCodeRedisDao.insertQrCodeWithCombo(new BigDecimal("9.90"), 2, 1, (byte)1, 50);
    }

    @Test
    public void selectQrCodeWithCombo() {
        System.out.println(qrCodeRedisDao.selectQrCodeWithCombo("9.90"));
    }

}
