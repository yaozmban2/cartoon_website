package utilsTest;

import cn.yu.cartoon.utils.FtpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/15 21:29
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class FtpUtilTest {

    @Test
    public void uploadTest() {
        String host = "192.168.204.140";
        int port = 21;
        String username = "ftpuser";
        String password = "a3154958";
        String sourceFilePath = "C:\\Users\\Yu\\IdeaProjects\\cartoon_website\\descomress\\2019-02-14";
        sourceFilePath = sourceFilePath.replace("\\", File.separator);
        String remoteDirPath = "";
        FtpUtil ftpUtil = new FtpUtil();
        try {
            ftpUtil.upload(host, port, username, password, sourceFilePath, remoteDirPath, "hdssie", true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}