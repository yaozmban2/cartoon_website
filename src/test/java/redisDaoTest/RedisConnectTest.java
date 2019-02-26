package redisDaoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/12 15:56
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class RedisConnectTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnectTest.class);

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void test(){

        //这里相当于redis对String类型的get操作
        String test = (String)redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }
}
