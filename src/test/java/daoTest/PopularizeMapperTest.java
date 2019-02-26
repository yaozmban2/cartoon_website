package daoTest;

import cn.yu.cartoon.dao.PopularizeMapper;
import cn.yu.cartoon.pojo.dto.Popularize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/23 16:24
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class PopularizeMapperTest {

    @Autowired
    PopularizeMapper popularizeMapper;

    @Test
    public void insertTest() {
        Popularize popularize = new Popularize();
        popularize.setInviteesId(2);
        popularize.setPreviousId(4);
        popularize.setInviteLevel((byte)6);
        popularizeMapper.insert(popularize);
    }
}
