package utilsTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/8 11:32
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class RandomTest {

    private static final Logger logger = LoggerFactory.getLogger(RandomTest.class);

    @Test
    public void test() {

        boolean result = false;
        File file = new File("c:\\asdasdasfasdad");
        if(!file.exists()){
            try {
                result = file.createNewFile();
            } catch (IOException e) {
                //e.printStackTrace();
                logger.error(e.getMessage(), e);
            }
        }
    }
}
