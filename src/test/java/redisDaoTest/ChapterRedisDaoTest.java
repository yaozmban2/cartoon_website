package redisDaoTest;

import cn.yu.cartoon.dao.ChapterMapper;
import cn.yu.cartoon.pojo.dto.Chapter;
import cn.yu.cartoon.redis.ChapterRedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/18 21:54
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class ChapterRedisDaoTest {

    @Autowired
    ChapterRedisDao chapterRedisDao;

    @Autowired
    ChapterMapper chapterMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void setId2ChapterNameTest() {

        Chapter chapter = new Chapter();
        chapter.setChapterId(10050);
        chapter.setChapterName("嘻嘻哈哈");

        chapterRedisDao.setId2ChapterName(chapter);
    }

    @Test
    public void getChapterNameByIdTest() {
        System.out.println(chapterRedisDao.getChapterNameById(10050));
    }

    /**
     * 测试向redis中设置章节数据
     * @author Yu
     * @date 13:32 2019/2/19
     **/
    @Test
    public void setChapterRecordTest() {
        //测试插入完整的chapter
        Chapter chapter = chapterMapper.selectChapterById(17);
        chapterRedisDao.setChapterRecord(chapter);

        //测试没有chapter内容时
        Chapter chapter2 = new Chapter();
        chapter2.setChapterId(150);
        chapterRedisDao.setChapterRecord(chapter2);
    }

    /**
     * 根据章节id获取章节数据的测试
     * @author Yu
     * @date 13:34 2019/2/19
     **/
    @Test
    public void getChapterRecordByIdTest() {
        Chapter chapter = null;
        try {
            chapter = chapterRedisDao.getChapterRecordById(170);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (null == chapter) {
            return;
        }
        System.out.println(chapter.getChapterId());
        System.out.println(chapter.getChapterName());
        System.out.println(chapter.getChapterUploadTime());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(chapter.getChapterUploadTime()));
    }

    @Test
    public void Test() {
        Map<String, String> map = new HashMap<>();
        map.put("hash1", "str1");
        map.put("hash2", "str2");
        map.put("hash3", "str3");
        map.put("hash4", "str4");
        map.put("hash5", "str5");
        redisTemplate.opsForHash().putAll("HashKey", map);
        redisTemplate.expire("HashKey", 60, TimeUnit.SECONDS);
    }
}
