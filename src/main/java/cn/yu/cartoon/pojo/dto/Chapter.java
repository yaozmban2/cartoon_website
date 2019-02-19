package cn.yu.cartoon.pojo.dto;

import java.util.Date;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/17 16:48
 **/
public class Chapter {

    private Integer chapterId;

    private String chapterName;

    private Date chapterUploadTime;

    private Integer cartoonId;

    private Integer chapterPrice;

    private String chapterUri;

    private Integer chapterNum;

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Date getChapterUploadTime() {
        return chapterUploadTime;
    }

    public void setChapterUploadTime(Date chapterUploadTime) {
        this.chapterUploadTime = chapterUploadTime;
    }

    public Integer getCartoonId() {
        return cartoonId;
    }

    public void setCartoonId(Integer cartoonId) {
        this.cartoonId = cartoonId;
    }

    public Integer getChapterPrice() {
        return chapterPrice;
    }

    public void setChapterPrice(Integer chapterPrice) {
        this.chapterPrice = chapterPrice;
    }

    public String getChapterUri() {
        return chapterUri;
    }

    public void setChapterUri(String chapterUri) {
        this.chapterUri = chapterUri;
    }

    public Integer getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }
}
