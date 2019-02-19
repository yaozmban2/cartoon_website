package cn.yu.cartoon.controller;

import cn.yu.cartoon.config.TempDirConfig;
import cn.yu.cartoon.pojo.dto.Chapter;
import cn.yu.cartoon.service.ChapterService;
import cn.yu.cartoon.utils.FilesUtils;
import cn.yu.cartoon.utils.ZipUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/18 11:05
 **/
@Controller("/chapter")
public class ChapterController {

    private static Logger logger = LoggerFactory.getLogger(ChapterController.class);

    private final ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    /**
     *  上传漫画单一章节的zip压缩文件
     *
     * @author Yu
     * @date 14:26 2019/2/19
     **/
    @ApiOperation("漫画章节上传")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "chapterName", value = "章节名字(必须)"),
            @ApiImplicitParam(paramType = "query", name = "chapterPrice", value = "章节金币价格"),
            @ApiImplicitParam(paramType = "query", name = "zipFile", value = "一章漫画的zip压缩文件"),
    })
    @PostMapping("/uploadChapter/{cartoonId}")
    @ResponseBody
    public String uploadChapter(@PathVariable Integer cartoonId, @RequestParam(value = "chapterName") String chapterName, @RequestParam(value = "chapterPrice") Integer chapterPrice,
                                 @RequestParam(value = "zipFile") MultipartFile zipFile) {
        if (null == zipFile) {
            return "请上传zip文件";
        }

        //将MultipartFile类型文件转换为File文件
        String filePath = null;
        try {
            filePath = FilesUtils.multifile2file(zipFile, TempDirConfig.getTempZipDirPath());
        } catch (IOException e) {
            logger.warn("method:uploadChapter，line:45 转为file文件错误！", e);
            return "请重新上传";
        }

        //判断是不是zip文件
        try {
            if (!ZipUtils.isArchiveFile(new File(filePath))) {
                return "请上传zip文件";
            }
        } catch (IOException e) {
            logger.warn("method:uploadChapter，line:53", e);
            return "请重新上传";
        }

        Chapter chapter = new Chapter();
        chapter.setCartoonId(cartoonId);
        chapter.setChapterName(chapterName);
        chapter.setChapterPrice(chapterPrice);
        chapter.setChapterUploadTime(new Date());

        //将zip章节数据存入数据库，章节漫画存入图片服务器
        try {
            chapterService.uploadChapterByZip(chapter, filePath, TempDirConfig.getTempDecompressDirPath());
        } catch (IOException e) {
            logger.warn("method:uploadChapter，line:59 上传章节失败！", e);
            return "请重新上传一次";
        }

        return "上传成功";
    }
}
