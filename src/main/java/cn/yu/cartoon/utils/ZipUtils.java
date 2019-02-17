package cn.yu.cartoon.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 对zip文件的一些操作
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/14 15:40
 **/
public class ZipUtils {

    private static byte[] ZIP_HEADER_1 = new byte[] { 80, 75, 3, 4 };
    private static byte[] ZIP_HEADER_2 = new byte[] { 80, 75, 5, 6 };

    /**
     *  判断是不是zip文件
     *
     * @author Yu
     * @date 15:42 2019/2/14
     * @param file 文件
     * @return boolean 是zip文件返回true 不是zip文件返回false
     **/
    public static boolean isArchiveFile(File file) throws IOException {

        int frontBytesLength = 4;

        if(file == null){
            return false;
        }

        if (file.isDirectory()) {
            return false;
        }

        boolean isArchive = false;
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            byte[] buffer = new byte[4];
            int length = input.read(buffer, 0, 4);
            if (frontBytesLength == length) {
                isArchive = (Arrays.equals(ZIP_HEADER_1, buffer)) || (Arrays.equals(ZIP_HEADER_2, buffer));
            }
        } finally {
            if (input != null) {
                input.close();
            }
        }

        return isArchive;
    }

    /**
     *  将zip文件压缩到指定的目录
     *
     * @author Yu
     * @date 16:41 2019/2/15
     * @param zipPath zip文件目录地址
     * @param descDir 指定解压目录
     * @return boolean 解压成功返回true 解压失败返回false
     **/
    public static boolean decompress(String zipPath, String descDir) {

        File zipFile = new File(zipPath);
        boolean flag = false;
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = null;

        try {
            //防止中文目录，乱码
            zip = new ZipFile(zipFile, Charset.forName("gbk"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryname = entry.getName();
                InputStream in = zip.getInputStream(entry);
                //指定解压后的文件夹+当前zip文件的名称
                String outPath = (descDir + "\\" + zipEntryname).replace("/", File.separator);
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if(new File(outPath).isDirectory()){
                    continue;
                }
                //保存文件路径信息（可利用md5.zip名称的唯一性，来判断是否已经解压）
                System.err.println("当前zip解压之后的路径为：" + outPath);
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while((len=in.read(buf1))>0){
                    out.write(buf1,0,len);
                }
                in.close();
                out.close();
            }
            flag = true;
            //必须关闭，要不然这个zip文件一直被占用着，要删删不掉，改名也不可以，移动也不行，整多了，系统还崩了。
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
