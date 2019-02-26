package serviceTest.chapterServiceTest;

import cn.yu.cartoon.pojo.dto.Chapter;
import cn.yu.cartoon.service.ChapterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Date;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/17 20:43
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class StorageChapterTest {

    @Autowired
    ChapterService chapterService;

    @Test
    public void Test1() {
        Chapter chapter = new Chapter();
        chapter.setChapterName("路过");
        chapter.setCartoonId(5);
        chapter.setChapterUploadTime(new Date());
        chapter.setChapterPrice(50);

        try {
            chapterService.storageChapter(chapter, "C:\\Users\\Yu\\IdeaProjects\\cartoon_website\\descomress\\2019-02-14\\photo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadChapterByZipTest() {
        Chapter chapter = new Chapter();
        chapter.setChapterName("路过");
        chapter.setCartoonId(5);
        chapter.setChapterUploadTime(new Date());
        chapter.setChapterPrice(50);

        String zipFilePath = "C:\\Users\\Yu\\IdeaProjects\\cartoon_website\\nothing\\德云色.zip";
        String decompressDirPath = "C:\\Users\\Yu\\IdeaProjects\\cartoon_website\\descomress";

        try {
            chapterService.uploadChapterByZip(chapter, zipFilePath, decompressDirPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
