package cn.yu.cartoon.controller;

import cn.yu.cartoon.pojo.dto.User;
import cn.yu.cartoon.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

/**
 * 用户注册相关的Controller
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/8 14:15
 **/
@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register (User user, @RequestParam(value="validatePassword") String validatePassword) {

        final int tooEasyPassword = 1;
        final int differentPassword = 2;
        final int userNameExists = 1;

        if (!registerService.isLegalName(user.getUserName())) {
            return "用户名不合法";
        }
        if ( tooEasyPassword == registerService.isLegalPassword(user.getUserPassword(), validatePassword)) {
            return "密码太简单";
        } else if ( differentPassword == registerService.isLegalPassword(user.getUserPassword(), validatePassword)) {
            return "两次输入的密码不一致";
        }
        if (registerService.validateUserName(user.getUserName())) {
            return "用户名已存在";
        }
        try {
            registerService.registerUser(user, "");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
            return "服务器出现了小问题，请联系管理员解决！";
        }

        return "注册成功";
    }

}
