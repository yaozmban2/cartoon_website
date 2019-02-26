package utilsTest;

import cn.yu.cartoon.config.TempDirConfig;
import cn.yu.cartoon.utils.FilesUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/18 12:05
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/application*.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class FilesUtilsTest {

    @Test
    public void multifile2fileTest() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("C:/Users/Yu/Desktop/Desktop.zip");
            MultipartFile file = new MockMultipartFile("C:/Users/Yu/Desktop/Desktop.zip", "Desktop.zip", "application/zip", fis);
            FilesUtils.multifile2file(file, TempDirConfig.getTempZipDirPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
