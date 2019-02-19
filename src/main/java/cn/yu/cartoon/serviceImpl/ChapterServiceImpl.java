package cn.yu.cartoon.serviceImpl;

import cn.yu.cartoon.config.CartoonFtpConfig;
import cn.yu.cartoon.dao.ChapterMapper;
import cn.yu.cartoon.pojo.dto.Chapter;
import cn.yu.cartoon.redis.ChapterRedisDao;
import cn.yu.cartoon.service.ChapterService;
import cn.yu.cartoon.utils.FtpUtil;
import cn.yu.cartoon.utils.RandomUtils;
import cn.yu.cartoon.utils.ZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/17 17:03
 **/
@Service("chapterService")
public class ChapterServiceImpl implements ChapterService {

    private static Logger logger = LoggerFactory.getLogger(ChapterServiceImpl.class);

    private final ChapterMapper chapterMapper;
    private final ChapterRedisDao chapterRedisDao;

    @Autowired
    public ChapterServiceImpl(ChapterMapper chapterMapper, ChapterRedisDao chapterRedisDao) {
        this.chapterMapper = chapterMapper;
        this.chapterRedisDao = chapterRedisDao;
    }

    @Override
    public boolean uriIsExit(String dirUri) {
        return null != chapterMapper.selectCountByUri(dirUri) && 0 != chapterMapper.selectCountByUri(dirUri);
    }

    @Override
    public void storageChapter(Chapter chapter, String chapterFilePath) throws IOException {

        //计算章节页数
        File sourceFile = new File(chapterFilePath);
        chapter.setChapterNum(Objects.requireNonNull(sourceFile.listFiles()).length);
        //生成章节图片目录名
        String dirUri = RandomUtils.randomString(8);
        while (uriIsExit(dirUri)) {
            dirUri = RandomUtils.randomString(8);
        }
        chapter.setChapterUri(dirUri);

        //如果上传成功，则将相关的信息存入数据库
        if (FtpUtil.upload(CartoonFtpConfig.host, CartoonFtpConfig.port, CartoonFtpConfig.userName, CartoonFtpConfig.password, chapterFilePath, CartoonFtpConfig.basePath, dirUri, true)) {
            //插入数据库
            chapterMapper.insert(chapter);
            //删除临时文件
            if(sourceFile.delete()) {
                logger.debug(sourceFile.getName() + " is deleted!");
            }else {
                logger.debug("Delete operation is failed.");
            }
        }
    }

    @Override
    public void uploadChapterByZip(Chapter chapter, String zipFilePath, String decompressDirPath) throws IOException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-ddHHmmss HH MM SS");
        String tempDirName = sf.format(new Date());
        if (ZipUtils.decompress(zipFilePath, decompressDirPath + File.separator + tempDirName)) {
            //删除临时的zip文件
            File zipFile = new File(zipFilePath);
            zipFile.delete();
            //存储到图片服务器
            storageChapter(chapter, decompressDirPath + File.separator + tempDirName);
        }
    }

    @Override
    public void insertChapterIntoRdis(Chapter chapter) {
        chapterRedisDao.setChapterRecord(chapter);
    }

}