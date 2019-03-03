package cn.yu.cartoon.controller;

import cn.yu.cartoon.utils.FilesUtils;
import cn.yu.cartoon.utils.ZipUtils;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 漫画相关操作
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/14 11:01
 **/

@RequestMapping("/cartoon")
public class CartoonController {

    private static Logger logger = LoggerFactory.getLogger(CartoonController.class);

    @ResponseBody
    @PostMapping("/upload")
    public String uploadCartoon(@RequestParam("zip") MultipartFile zip, HttpServletRequest request){

        //如果传入的数据是空
        if (null == zip || zip.isEmpty()) {
            return "传入的数据为空";
        }

        //将MultipartFile转为File
        String fileURI = null;
        try {
            fileURI = FilesUtils.multifile2file(zip, "sdasda");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return "服务器出了点小问题";
        }
        if (null == fileURI) {
            return "服务器出了点小问题";
        }
        File zipFile = new File(fileURI);
        try {
            if (!ZipUtils.isArchiveFile(zipFile)) {
                return "不是zip文件";
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return "服务器出了点小问题";
        }

        return "是zip文件";
    }
}
