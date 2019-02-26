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
 * @date 2019/2/7 17:21
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class isLegalEmail {

    @Autowired
    RegisterService registerService;

    @Test
    public void test1() {
        String email = "213123123123@qq.com";
        System.out.println(registerService.isLegalEmail(email));
    }

}
