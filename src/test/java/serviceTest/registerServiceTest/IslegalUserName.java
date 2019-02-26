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
 * @date 2019/2/7 16:52
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class IslegalUserName {

    @Autowired
    RegisterService registerService;

    @Test
    public void Test() {
        String userName = "yaosskadjkj你好fdg";
        System.out.println(registerService.isLegalName(userName));
    }
}
