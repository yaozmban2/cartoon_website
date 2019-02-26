package cn.yu.cartoon.controller;

import cn.yu.cartoon.config.TempDirConfig;
import cn.yu.cartoon.pojo.dto.Chapter;
import cn.yu.cartoon.pojo.vo.BaseResultHelper;
import cn.yu.cartoon.pojo.vo.chaptervo.ChapterInfoVo;
import cn.yu.cartoon.service.ChapterService;
import cn.yu.cartoon.utils.FilesUtils;
import cn.yu.cartoon.utils.ZipUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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
            @ApiImplicitParam(paramType = "query", name = "chapterName", value = "章节名字(必须)", required = true),
            @ApiImplicitParam(paramType = "query", name = "chapterPrice", value = "章节金币价格", required = true),
    })
    @PostMapping("/chapter/{cartoonId}")
    @ResponseBody
    public BaseResultHelper<ChapterInfoVo> uploadChapter(@PathVariable Integer cartoonId,
                                                         @RequestParam(value = "chapterName") String chapterName,
                                                         @RequestParam(value = "chapterPrice") Integer chapterPrice,
                                                         @ApiParam(value = "上传的文件", required = true) MultipartFile zipFile) {
        BaseResultHelper<ChapterInfoVo> resultHelper = new BaseResultHelper<>();

        if (null == zipFile) {
            resultHelper.setCode("FAIL");
            resultHelper.setMsg("zip文件为空");
            return resultHelper;
        }

        if (null == chapterName || "".equals(chapterName)) {
            resultHelper.setCode("FAIL");
            resultHelper.setMsg("没有输入章节名称");
            return resultHelper;
        }

        if (null == chapterPrice) {
            resultHelper.setCode("FAIL");
            resultHelper.setMsg("输入价格错误");
            return resultHelper;
        }

        //将MultipartFile类型文件转换为File文件
        String filePath = null;
        try {
            filePath = FilesUtils.multifile2file(zipFile, TempDirConfig.getTempZipDirPath());
        } catch (IOException e) {
            logger.warn("method:uploadChapter，line:45 转为file文件错误！", e);
            resultHelper.setCode("FAIL");
            resultHelper.setMsg("服务器出错，请重新上传");
            return resultHelper;
        }

        //判断是不是zip文件
        try {
            if (!ZipUtils.isArchiveFile(new File(filePath))) {
                resultHelper.setCode("FAIL");
                resultHelper.setMsg("上传的不是zip文件");
                return resultHelper;
            }
        } catch (IOException e) {
            logger.warn("method:uploadChapter，line:53", e);
            resultHelper.setCode("FAIL");
            resultHelper.setMsg("服务器出错，请重新上传");
            return resultHelper;
        }

        Chapter chapter = new Chapter();
        chapter.setCartoonId(cartoonId);
        chapter.setChapterName(chapterName);
        chapter.setChapterPrice(chapterPrice);
        chapter.setChapterUploadTime(new Date());

        Chapter returnChapter = null;
        //将zip章节数据存入数据库，章节漫画存入图片服务器
        try {
            returnChapter = chapterService.uploadChapterByZip(chapter, filePath, TempDirConfig.getTempDecompressDirPath());
        } catch (IOException e) {
            logger.warn("method:uploadChapter，line:59 上传章节失败！", e);
            resultHelper.setCode("FAIL");
            resultHelper.setMsg("服务器出错，请重新上传");
            return resultHelper;
        }
        if (null != returnChapter) {
            ChapterInfoVo chapterInfoVo = new ChapterInfoVo();
            chapterInfoVo.setChapterId(returnChapter.getChapterId());
            chapterInfoVo.setChapterUri(returnChapter.getChapterUri());
            resultHelper.setCode("SUCCESS");
            resultHelper.setMsg("上传成功");
            resultHelper.setData(chapterInfoVo);
            return resultHelper;
        }
        resultHelper.setCode("FAIL");
        resultHelper.setMsg("上传失败，请重新上传");
        return resultHelper;

    }

    /**
     *  获得漫画章节的信息
     *
     * @author Yu
     * @date 21:36 2019/2/19
     **/
    @ApiOperation("获得漫画章节的基本信息")
    @GetMapping("/chapter/chapterInfo/{chapterId}")
    @ResponseBody
    public BaseResultHelper<Chapter> getChapterInfo(@PathVariable Integer chapterId) {

        BaseResultHelper<Chapter> result =  new BaseResultHelper<>();
        Chapter chapter;
        try {
            chapter = chapterService.getChapterById(chapterId);
        } catch (ParseException e) {
            logger.warn("从redis中读取的时间字符串转换为Date数据时出错", e);
            result.setCode("FAIL");
            result.setMsg("服务器出错，请刷新重试");
            return result;
        }
        result.setCode("SUCCESS");
        result.setMsg("漫画章节信息获取成功");
        result.setData(chapter);
        return result;
    }
}
