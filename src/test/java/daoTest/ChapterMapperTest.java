package daoTest;

import cn.yu.cartoon.dao.ChapterMapper;
import cn.yu.cartoon.pojo.dto.Chapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/17 20:16
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class ChapterMapperTest {

    @Autowired
    ChapterMapper chapterMapper;

    @Test
    public void insertTest() {
        Chapter chapter = new Chapter();
        chapter.setChapterName("路过");
        chapter.setCartoonId(5);
        chapter.setChapterUploadTime(new Date());
        chapter.setChapterPrice(50);
        chapter.setChapterUri("sdsadds");
        chapter.setChapterNum(20);
        chapterMapper.insert(chapter);
    }
}
