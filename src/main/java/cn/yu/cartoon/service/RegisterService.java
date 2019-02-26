package cn.yu.cartoon.service;

import cn.yu.cartoon.pojo.dto.User;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * 注册用户的服务
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/7 15:27
 **/
public interface RegisterService {

    /**
     * 验证用户名的有效性
     *
     * @author Yu
     * @date 15:32 2019/2/7
     * @param username 用户填写的用户名
     * @return boolen 合法的用户名返回true  不合法的用户名返回false
     **/
    boolean isLegalName(String username);

    /**
     *  验证输入的密码的有效性
     *
     * @author Yu
     * @date 15:40 2019/2/7
     * @param password 输入的密码
     * @param validatePassword 第二次输入的校验密码
     * @return int 返回0 表示密码没问题，返回1 表示密码太简单，返回2 表示输入的两次密码不一致
     **/
    int isLegalPassword(String password, String validatePassword);

    /**
     *  验证输入的电子邮箱的有效性
     *
     * @author Yu
     * @date 17:17 2019/2/7
     * @param email 输入的电子邮箱
     * @return boolen 合法的邮箱返回true 不合法的邮箱返回false
     **/
    boolean isLegalEmail(String email);

    /**
     *  验证用户填写的用户名是否已存在
     *
     * @author Yu
     * @date 17:43 2019/1/27
     * @param userName 填写的用户注册信息
     * @return boolean 如果用户名未存在 返回true 如果存在用户名 返回false
     **/
    boolean validateUserName(String userName);

    /**
     *  验证用户填写的邮箱是否已存在
     *
     * @author Yu
     * @date 23:13 2019/2/8
     * @param userEmail String 填写的用户邮箱
     * @return boolean 如果用户填写的邮箱可用 返回true 如果用户填写的邮箱已注册 返回false
     **/
    boolean validateUserEmail(String userEmail);

    /**
     *  检查用户输入的注册信息是否有效
     *
     * @author Yu
     * @date 16:28 2019/2/25
     * @param user 用户输入的注册信息
     * @param validatePassword 二次输入的验证密码
     * @return int 用户注册信息没问题  返回0， 用户名不合法 返回1， 用户名已存在 返回2， 密码太简单 返回3， 两次输入的密码不一致 返回4， 用户邮箱不合法 返回5， 用户邮箱已存在 返回6
     **/
    int checkRegisterInfo(User user, String validatePassword);

    /**
     *  将用户信息存入数据库中
     *
     * @author Yu
     * @date 19:07 2019/1/27
     * @param user 用户表单信息
     * @param validatePassword 二次验证密码
     * @param request HTTP的请求对象
     * @return int 用户注册成功  返回0， 用户名不合法 返回1， 用户名已存在 返回2， 密码太简单 返回3， 两次输入的密码不一致 返回4， 用户邮箱不合法 返回5， 用户邮箱已存在 返回6，没有Md5算法出现异常 返回7
     **/
    int registerUser(User user, String validatePassword, HttpServletRequest request);

    /**
     *  根据推广代码获得用户的信息
     *
     * @author Yu
     * @date 13:59 2019/2/23
     * @param popularizedCode 推广者的推广代码
     * @return User 用户信息
     **/
    User getUserByPopularizedCode(String popularizedCode);

    /**
     * 根据分销级别 将邀请者和受邀者还有分成级别存入数据库
     *
     * @author Yu
     * @date 15:56 2019/2/23
     * @param previousId 邀请者id
     * @param inviteeId 受邀者id
     * @param level 分成级别
     **/
    void setPopularize(int previousId, int inviteeId, Byte level);

}
