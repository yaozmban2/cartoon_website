package serviceTest.registerServiceTest;

import cn.yu.cartoon.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/7 16:37
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class IslegalPassword {

    @Autowired
    RegisterService registerService;

    /**
     *
     *
     * @author Yu
     * @date 16:50 2019/2/7
     * @param
     * @return
     **/
    @Test
    public void test1() {
        String password = "13543543874354354";
        String validatePassword = "13543543874354354";
        System.out.println(registerService.isLegalPassword(password, validatePassword));
    }
}
