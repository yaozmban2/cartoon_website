package cn.yu.cartoon.pojo.vo.chaptervo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 展示漫画章节基础信息的vo类
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/19 21:38
 **/
@ApiModel(value = "ChapterInfoVo", description = "漫画章节的基本信息")
public class ChapterInfoVo {

    @ApiModelProperty(name = "chapterId", notes = "章节的id")
    private Integer chapterId;

    @ApiModelProperty(name = "chapterUri", notes = "章节文件夹的名称")
    private String chapterUri;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterUri() {
        return chapterUri;
    }

    public void setChapterUri(String chapterUri) {
        this.chapterUri = chapterUri;
    }
}
