package cn.yu.cartoon.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ftp的工具类
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/15 20:53
 **/
public class FtpUtil {

    private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    /**
     *  上传到ftp服务器
     *
     * @author Yu
     * @date 14:57 2019/2/16
     * @param host ftp服务器的ip地址
     * @param port ftp服务器的端口
     * @param username 登录用户名
     * @param password 登录用户密码
     * @param sourceFilePath 源文件目录地址
     * @param remoteDirPath 服务器目录地址，  例如根目录传入空字符串""   根目录下的images文件夹传入"/images"
     * @param newRemoteDirName 新的目录名称   传入文件夹名称即可 如"photo"
     * @param deleteSourceFile 是否删除源文件  填入true则删除源文件
     * @return boolean 上传成功返回true 上传失败返回false
     * @throws IOException IO错误
     **/
    public static boolean upload(String host, int port, String username, String password, String sourceFilePath, String remoteDirPath, String newRemoteDirName, boolean deleteSourceFile) throws IOException {

        FTPClient ftpClient = new FTPClient();
        int reply;

        try {
            //连接ftp服务器
            ftpClient.connect(host, port);
            //使用用户名密码登录
            ftpClient.login(username, password);
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.warn("FTP服务无法连接！");
                return false;
            }
        } catch (IOException e) {
            ftpClient.disconnect();
            throw new IOException("host = " + host + "，port = " + port + "，username = " + username + "，password = " + password + "  ==>" + "连接服务器失败！", e);
        }
        File sourceFile = new File(sourceFilePath);
        //判断源文件是不是文件夹
        if (!sourceFile.isDirectory()) {
            //不是文件夹就返回false
            logger.debug("不是文件夹");
            return false;
        }
        //如果是文件夹
        //将文件夹存入ftp服务器
        refreshFileList(sourceFile, (remoteDirPath + "/" + newRemoteDirName), ftpClient, deleteSourceFile);

        ftpClient.logout();

        return true;
    }

    /**
     *  将文件夹上传到图片服务器中，并删除源文件
     *
     * @author Yu
     * @date 15:41 2019/2/16
     * @param sourceFile 源文件对象
     * @param remoteDirPath 远程服务器目录地址
     * @param ftpClient ftp连接对象
     * @param deleteSourceFile 选择是否删除源文件
     **/
    private static void refreshFileList(File sourceFile, String remoteDirPath, FTPClient ftpClient, boolean deleteSourceFile) {

        File[] files = sourceFile.listFiles();
        if (null == files) {
            logger.debug("文件夹中没有文件！");
            return;
        }
        for (File file : files) {
            String sourceFilePath = file.getPath();
            String excessivePath = sourceFilePath.substring(sourceFilePath.lastIndexOf(File.separator) + 1);
            String remotePathCurrent = remoteDirPath + "/" + excessivePath;
            if (file.isDirectory()) {
                //是文件夹,则递归
                //递归文件夹！！！
                refreshFileList(file, remotePathCurrent, ftpClient, deleteSourceFile);
            } else {
                //是文件,则执行上传到FTP方法
                process(file, remotePathCurrent, remoteDirPath, ftpClient, deleteSourceFile);
            }
        }
    }

    /**
     *  上传一个文件到ftp服务器，并将源文件删除
     *
     * @author Yu
     * @date 14:34 2019/2/16
     * @param sourceFile 源文件对象
     * @param remoteFilePath 远程文件地址
     * @param remoteDirPath 远程文件夹地址
     * @param ftpClient ftp连接对象
     * @param deleteSourceFile 是否删除源文件
     **/
    private static void process(File sourceFile, String remoteFilePath, String remoteDirPath, FTPClient ftpClient, boolean deleteSourceFile){
        InputStream input = null;
        try {
            //切换到上传目录
            if (!ftpClient.changeWorkingDirectory(remoteDirPath)) {
                //如果目录不存在创建目录
                String[] dirs = remoteDirPath.split("/");
                //拼接要生成的目录
                StringBuilder tempPathBuild = new StringBuilder();
                tempPathBuild.append("/");
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPathBuild.append(dir).append("/");
                    String tempPath = tempPathBuild.toString();

                    if (!ftpClient.changeWorkingDirectory(tempPath)) {
                        if (!ftpClient.makeDirectory(tempPath)) {
                            logger.debug("生成文件夹失败！");
                        } else {
                            ftpClient.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            input = new FileInputStream(sourceFile);
            //上传文件
            if (!ftpClient.storeFile(remoteFilePath, input)) {
                logger.debug("上传文件失败！");
            }
        } catch (IOException e) {
            logger.warn("文件输入流创建失败！", e);
        } finally {
            try {
                assert input != null;
                input.close();
                if (deleteSourceFile) {
                    sourceFile.delete();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
