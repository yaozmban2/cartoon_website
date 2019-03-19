package daoTest;

import cn.yu.cartoon.dao.QRCodeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/4 16:54
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class QRCodeMapperTest {

    @Autowired
    QRCodeMapper qrCodeMapper;

    @Test
    public void selectQRCodeByPrice() {
        System.out.println(qrCodeMapper.selectQRCodeByPrice(10));
    }

}
