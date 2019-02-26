package controllerTest;

import cn.yu.cartoon.controller.ChapterController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/18 15:09
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:application/applicationContext.xml", "classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class ChapterControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ChapterController chapterController;//注入要测试的Controller

    //这个方法在每个方法执行之前都会执行一遍
    @Before
    public void setup() {
        //初始化MockMvc对象
        //mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        //两种方式都可以初始化mockMvc，推荐用这种，上面的会自动跳转地址status判断方法无效
        mockMvc = MockMvcBuilders.standaloneSetup(chapterController).build();
    }

    @Test
    public void uploadChapterTest() throws IOException {

        FileInputStream fis = new FileInputStream("C:/Users/Yu/Desktop/德云色.zip");
        MultipartFile file = new MockMultipartFile("C:/Users/Yu/Desktop/德云色.zip", "德云色.zip", "application/zip", fis);

        System.out.println(chapterController.uploadChapter(6, "爱你哟", 100, file));
    }
}
