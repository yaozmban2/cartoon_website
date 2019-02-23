package cn.yu.cartoon.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件工具
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/14 18:06
 **/
public class FilesUtils {

    /**
     *  将MultipartFile存入硬盘并返回文件名
     *
     * @author Yu
     * @date 20:18 2019/2/14
     * @param multipartFile multipartFile文件
     * @param tempZipPath 临时存放zip文件的文件夹地址
     * @return String 文件地址
     **/
    public static String multifile2file(MultipartFile multipartFile, String tempZipPath) throws IOException {

        String returnFileName = null;
        if (multipartFile != null) {
            //获得原始文件名;
            String saveFileName = multipartFile.getOriginalFilename();
            File path = new File(tempZipPath);
            //判断文件路径下的文件夹是否存在，不存在则创建
            if (!path.exists()) {
                path.mkdirs();
            }
            //存放的文件地址
            returnFileName = tempZipPath + File.separator + saveFileName;
            File savedFile = new File(returnFileName);
            multipartFile.transferTo(savedFile);
        }else {
            System.out.println("文件是空的");
        }

        return returnFileName;
    }
}
