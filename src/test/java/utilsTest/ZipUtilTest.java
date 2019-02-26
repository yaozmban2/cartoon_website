package utilsTest;

import cn.yu.cartoon.utils.ZipUtils;
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
 * @date 2019/2/15 17:53
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class ZipUtilTest {

    @Test
    public void decompressTest() {
        /*String zipPath = "C:\\Users\\Yu\\IdeaProjects\\cartoon_website\\nothing\\2019-02-14.zip";
        String descDir = "C:\\Users\\Yu\\IdeaProjects\\cartoon_website\\descomress\\2019-02-14";
        try {
            ZipUtils.decompress(zipPath, descDir);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        String rootPath = getClass().getResource(File.separator).toString().replace("file:/", "").replace("WEB-INF/classes/", "");
        System.out.println(rootPath);

    }

}
