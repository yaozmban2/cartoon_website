package utilsTest;

import cn.yu.cartoon.utils.Encryption;
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
 * @date 2019/2/8 11:13
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class EncryptionTest {

    @Test
    public void  test() {
        String password = "sadkasdas";
        String salt = "154";
        try {
            System.out.println(Encryption.md5(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
