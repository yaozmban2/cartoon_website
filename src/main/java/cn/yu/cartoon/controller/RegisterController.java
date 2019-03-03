package cn.yu.cartoon.controller;

import cn.yu.cartoon.pojo.dto.User;
import cn.yu.cartoon.pojo.vo.BaseResultHelper;
import cn.yu.cartoon.service.RegisterService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户名称", name = "userName", required = true),
            @ApiImplicitParam(value = "用户邮箱", name = "email", required = true),
            @ApiImplicitParam(value = "用户密码", name = "password", required = true),
            @ApiImplicitParam(value = "二次验证密码", name = "validatePassword", required = true)
    })
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResultHelper register (@RequestParam(value = "userName") String userName,
                                      @RequestParam(value = "email") String email,
                                      @RequestParam(value = "password") String password,
                                      @RequestParam(value="validatePassword") String validatePassword,
                                      HttpServletRequest request) {

        final int userNameIsIllegal = 1;
        final int userNameExists = 2;
        final int tooEasyPassword = 3;
        final int differentPassword = 4;
        final int emailIsIllegal = 5;
        final int userEmailExists = 6;
        final int serverError = 7;

        //设置对象数据
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setUserEmail(email);

        BaseResultHelper result = new BaseResultHelper();
        int registerResult = registerService.registerUser(user, validatePassword, request);

        if (userNameIsIllegal == registerResult) {
            result.setCode("FAIL");
            result.setMsg("用户名不合法");
            return result;
        }
        if ( tooEasyPassword == registerResult)  {
            result.setCode("FAIL");
            result.setMsg("密码太简单");
            return result;
        }
        if ( differentPassword == registerResult) {
            result.setCode("FAIL");
            result.setMsg("两次输入的密码不一致");
            return result;
        }
        if (userNameExists == registerResult) {
            result.setCode("FAIL");
            result.setMsg("用户名已存在");
            return result;
        }
        if (emailIsIllegal == registerResult) {
            result.setCode("FAIL");
            result.setMsg("用户邮箱不合法");
            return result;
        }
        if (userEmailExists == registerResult) {
            result.setCode("FAIL");
            result.setMsg("用户邮箱已存在");
            return result;
        }

        if (serverError == registerResult) {
            result.setCode("FAIL");
            result.setMsg("服务器出现了小问题，请重试");
            return result;
        }

        result.setCode("SUCCESS");
        result.setMsg("用户注册成功");
        return result;
    }

}
