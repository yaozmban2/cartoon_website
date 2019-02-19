package cn.yu.cartoon.service;

import cn.yu.cartoon.pojo.dto.Chapter;

import java.io.IOException;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/17 17:03
 **/
public interface ChapterService {

    /**
     *  判断目录地址的名称是否已经存在
     *
     * @author Yu
     * @date 17:12 2019/2/17
     * @param dirUri 目录地址
     * @return boolean 存在目录地址则返回true，没有返回false
     **/
    boolean uriIsExit(String dirUri);

    /** 
     *  将章节漫画文件夹存到远程服务器中，并将章节数据存到数据库中
     *
     * @author Yu
     * @date 17:28 2019/2/17
     * @param chapter 章节数据信息 除了chapterUri属性都要填入
     * @param chapterFilePath 漫画章节文件夹地址
     * @throws IOException 输入输出错误
     **/
    void storageChapter(Chapter chapter, String chapterFilePath) throws IOException;

    /**
     * 将zip压缩的漫画章节解压并上传到ftp图片服务器中
     *
     * @author Yu
     * @date 22:00 2019/2/17
     * @param chapter 章节信息
     * @param zipFilePath 漫画章节的zip文件地址
     * @param decompressDirPath 存放解压文件的临时地址
     * @throws IOException IO异常
     **/
    void uploadChapterByZip(Chapter chapter, String zipFilePath, String decompressDirPath) throws IOException;

    void insertChapterIntoRdis(Chapter chapter);

}
