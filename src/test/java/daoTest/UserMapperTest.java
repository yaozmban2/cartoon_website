package daoTest;

import cn.yu.cartoon.dao.UserMapper;
import cn.yu.cartoon.pojo.dto.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/8 11:56
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1() {
        String popularize = "SADAFW";
        System.out.println(userMapper.selectUserByUserPopularize(popularize));
    }

    @Test
    public void test2() {
        User user = new User();
        user.setUserName("asdadasfa");
        user.setUserPassword("sadasdasdasfasda");
        user.setUserPopularize("Vw8jqg");
        /*try {
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            System.out.println("错误的用户名");
        }*/
        userMapper.insert(user);
    }

    @Test
    public void test3() {
        User user = userMapper.selectUserByUserPopularize("Vw8jqg");
        if (null == user) {
            System.out.println("没有这个记录");
        }
    }

    @Test
    public void selectUserByUserPopularizeTest(){

    }
}
