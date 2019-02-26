package controllerTest;

import cn.yu.cartoon.controller.CartoonController;
import cn.yu.cartoon.utils.FilesUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/14 17:24
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class CartoonControllerTest {

    @Autowired
    CartoonController cartoonController;

    @Test
    public void testUpload() {

        MockMultipartHttpServletRequest request = new MockMultipartHttpServletRequest();

        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.addHeader("Content-type", "multipart/form-data");
        try {
            FileInputStream fis = new FileInputStream("C:/Users/Yu/Desktop/Desktop.zip");
            MockMultipartFile mfile = new MockMultipartFile("C:/Users/Yu/Desktop/Desktop.zip", "Desktop.zip", "application/zip", fis);
            System.out.println(cartoonController.uploadCartoon(mfile, request));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
