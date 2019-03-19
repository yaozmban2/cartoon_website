package redisDaoTest;

import cn.yu.cartoon.redis.ComboRedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/4 16:09
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class ComboRedisDaoTest {

    @Autowired
    ComboRedisDao comboRedisDao;

    @Test
    public void insertUserWithComboTest() {
        comboRedisDao.insertUserWithCombo(2, 3, "http://adadasd/dsadasd.jpg");
    }

    @Test
    public void selectUserWithCombo() {
        if (null == comboRedisDao.selectUserWithCombo(2, 5)) {
            System.out.println("没有这个数据！");
        }
        System.out.println(comboRedisDao.selectUserWithCombo(2, 5));
    }

}
