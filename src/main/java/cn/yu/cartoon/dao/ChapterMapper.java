package cn.yu.cartoon.dao;

import cn.yu.cartoon.pojo.dto.Chapter;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/17 16:55
 **/
public interface ChapterMapper {

    /**
     *  根据章节目录地址来查看记录总数
     *
     * @author Yu
     * @date 17:01 2019/2/17
     * @param chapterUri 章节目录地址
     * @return Integer返回记录条数
     **/
    Integer selectCountByUri(String chapterUri);

    /**
     *  将漫画章节信息存入数据库
     *
     * @author Yu
     * @date 19:18 2019/2/17
     * @param chapter 章节对象
     **/
    void insert(Chapter chapter);

    /**
     *  根据章节id查找到章节数据
     *
     * @author Yu
     * @date 11:16 2019/2/19
     * @param chapterId 章节id
     * @return Chapter 章节数据
     **/
    Chapter selectChapterById(Integer chapterId);

    /**
     *  根据uri的值查找到章节数据
     *
     * @author Yu
     * @date 17:02 2019/2/19
     * @param chapterUri 章节地址的文件夹名称
     * @return Chapter
     **/
    Chapter selectChapterByUri(String chapterUri);
}
