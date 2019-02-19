package cn.yu.cartoon.config;

/**
 * 存放一些临时文件路径的类
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/18 11:49
 **/
public class TempDirConfig {

    private static String tempZipDirPath;

    private static String tempDecompressDirPath;

    static {
        String relativelyPath = System.getProperty("user.dir");
        tempZipDirPath  = relativelyPath + "\\temZipDir";
        tempDecompressDirPath = relativelyPath + "temDepressDir";
    }

    public static String getTempZipDirPath() {
        return tempZipDirPath;
    }

    public static String getTempDecompressDirPath() {
        return tempDecompressDirPath;
    }
}
