package serviceTest.registerServiceTest;

import cn.yu.cartoon.dao.UserMapper;
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
 * @date 2019/2/8 23:42
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class ValidateUserNameTest {

    @Autowired
    RegisterService registerService;

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() {
        String username = "刘昊";
        System.out.println(registerService.validateUserName(username));
    }
}
