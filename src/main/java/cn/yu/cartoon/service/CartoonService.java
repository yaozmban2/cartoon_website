package cn.yu.cartoon.service;

import java.io.File;

/**
 * 漫画服务层
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/14 20:55
 **/
public interface CartoonService {

    /**
     *  将上传的zip文件解压缩，并上传到图片服务器
     *
     * @author Yu
     * @date 15:29 2019/2/17
     * @param zipFile zip文件
     * @return
     **/
    boolean uploadCartoonByZipFile(File zipFile);
}
