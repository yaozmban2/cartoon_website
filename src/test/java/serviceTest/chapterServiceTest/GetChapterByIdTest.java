package serviceTest.chapterServiceTest;

import cn.yu.cartoon.pojo.dto.Chapter;
import cn.yu.cartoon.service.ChapterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.ParseException;

/**
 * 测试 ChapterService类的 GetChapterById方法
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/19 19:58
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class GetChapterByIdTest {

    @Autowired
    ChapterService chapterService;

    /**
     *  测试数据不在redis但在mysql中有的情况
     *
     * @author Yu
     * @date 19:59 2019/2/19
     **/
    @Test
    public void notInRedisButInMysql() {
        try {
            long startTime=System.currentTimeMillis();
            Chapter chapter = chapterService.getChapterById(14);
            long endTime=System.currentTimeMillis();
            System.out.println("程序运行时间： "+(startTime - endTime)+"ms");
            System.out.println(chapter.getChapterId());
            System.out.println(chapter.getChapterName());
            System.out.println(chapter.getChapterUploadTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     *  测试mysql中没有的数据（缓存穿透）
     *
     * @author Yu
     * @date 20:58 2019/2/19
     **/
    @Test
    public void noRecord() {
        try {
            long startTime=System.currentTimeMillis();
            Chapter chapter = chapterService.getChapterById(100);
            long endTime=System.currentTimeMillis();
            System.out.println("程序运行时间： "+(startTime - endTime)+"ms");
            System.out.println(chapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
