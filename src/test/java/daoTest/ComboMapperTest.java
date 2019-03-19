package daoTest;

import cn.yu.cartoon.dao.ComboMapper;
import cn.yu.cartoon.pojo.dto.Combo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 15:08
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class ComboMapperTest {

    @Autowired
    ComboMapper comboMapper;

    @Test
    public void insertAndGetIdTest() {
        Combo combo = new Combo();
        combo.setComboPrice(20);
        combo.setComboCurrency(0);
        combo.setIsDelete((byte)0);
        combo.setVipDay(30);
        combo.setComboType((byte)2);

        int result = comboMapper.insertAndGetId(combo);
        System.out.println(combo.getComboId());
    }

    @Test
    public void selectById() {

        Combo combo = comboMapper.selectById(50);

        if (combo != null) {
            System.out.println(combo.getComboType());
        } else {
            System.out.println("返回的是个空指针！");
        }


    }


}
