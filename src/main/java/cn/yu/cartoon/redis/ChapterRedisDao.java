package cn.yu.cartoon.redis;

import cn.yu.cartoon.pojo.dto.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 漫画章节的redis数据操作
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/18 15:55
 **/
@Repository
public class ChapterRedisDao {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 5;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    /**
     *  设置章节id和章节名字的映射
     *
     * @author Yu
     * @date 21:50 2019/2/18
     * @param chapter 章节数据
     **/
    public void setId2ChapterName(Chapter chapter) {

        Integer chapterId = chapter.getChapterId();
        int superiorId = chapterId / 500;
        int subId = chapterId % 500;

        String id2Name = "chapter:chapterName:{0}";
        String h = MessageFormat.format(id2Name, String.valueOf(superiorId));
        redisTemplate.opsForHash().put(h, String.valueOf(subId), chapter.getChapterName());
    }

    /**
     *  根据章节id获得章节名字
     *
     * @author Yu
     * @date 22:51 2019/2/18
     * @param id 章节id
     * @return String 章节名字
     **/
    public String getChapterNameById(int id) {
        int superiorId = id / 500;
        int subId = id % 500;

        String id2Name = "chapter:chapterName:{0}";
        String h = MessageFormat.format(id2Name, String.valueOf(superiorId));
        return (String) redisTemplate.opsForHash().get(h, String.valueOf(subId));
    }


    /**
     * 将章节信息存入redis中
     * 如果传入的chapter只有一个chapterId则表示是为了解决缓存穿透问题传入的数据
     * 设置缓存时间为5分钟
     *
     * @author Yu
     * @date 23:17 2019/2/18
     * @param chapter 章节信息
     **/
    public void setChapterRecord(Chapter chapter) {

        //设置Hkey  recordH
        String recordName = "chapter:{0}";
        String recordH = MessageFormat.format(recordName, String.valueOf(chapter.getChapterId()));
        //如果传入的chapter只有一个chapterId，则传入一个non的key，设置过期时间为5分钟
        if (null == chapter.getChapterName()) {
            redisTemplate.opsForHash().put(recordH, "non", "");
            redisTemplate.expire(recordH, DEFAULT_EXPIRE, TimeUnit.SECONDS);
            return;
        }
        //设置记录各字段的值
        Map<String, String> tempMap = new HashMap<>(6);
        tempMap.put("chapterName", chapter.getChapterName());
        tempMap.put("chapterUploadTime", String.valueOf(chapter.getChapterUploadTime()));
        tempMap.put("cartoonId", String.valueOf(chapter.getCartoonId()));
        tempMap.put("chapterPrice", String.valueOf(chapter.getChapterPrice()));
        tempMap.put("chapterUri", chapter.getChapterUri());
        tempMap.put("chapterNum", String.valueOf(chapter.getChapterNum()));
        redisTemplate.opsForHash().putAll(recordH, tempMap);

    }

    /**
     *  根据章节id获得章节数据
     *
     * @author Yu
     * @date 23:43 2019/2/18
     * @param id 章节id
     * @return Chapter 章节相关数据
     * @throws ParseException 字符串日期转换成Date数据时出错
     **/
    public Chapter getChapterRecordById(int id) throws ParseException {

        //设置Hkey  recordH
        String recordName = "chapter:{0}";
        String recordH = MessageFormat.format(recordName, String.valueOf(id));
        //获取记录数据
        Map<String, String> tempMap = redisTemplate.opsForHash().entries(recordH);
        //如果redis中没数据则返回
        if (0 == tempMap.size()) {
            return null;
        }
        Chapter chapter = new Chapter();
        //如果返回的hash只有一个键值对，则表明mysql中没有这个数据
        if (1 == tempMap.size()) {
            chapter.setChapterId(id);
            return chapter;
        }
        chapter.setChapterId(id);
        chapter.setChapterName(tempMap.get("chapterName"));
        chapter.setChapterUploadTime(new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK).parse(tempMap.get("chapterUploadTime")));
        chapter.setCartoonId(Integer.valueOf(tempMap.get("cartoonId")));
        chapter.setChapterPrice(Integer.valueOf(tempMap.get("chapterPrice")));
        chapter.setChapterUri(tempMap.get("chapterUri"));
        chapter.setChapterNum(Integer.valueOf(tempMap.get("chapterNum")));
        return chapter;
    }
}
