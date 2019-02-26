package serviceTest.registerServiceTest;

import cn.yu.cartoon.pojo.dto.User;
import cn.yu.cartoon.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.security.NoSuchAlgorithmException;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/8 12:19
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class RegisterUser {

    @Autowired
    RegisterService registerService;

    @Test
    public  void  test() {
        User user = new User();
        user.setUserName("撒大大吖试点");
        user.setUserPassword("aqq22132");

    }
}
