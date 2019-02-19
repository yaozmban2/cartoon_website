package cn.yu.cartoon.service;

import cn.yu.cartoon.pojo.dto.User;

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
     *  将用户信息存入数据库中
     *
     * @author Yu
     * @date 19:07 2019/1/27
     * @param user 用户表单信息
     * @param popularize String 推广标识
     * @throws NoSuchAlgorithmException 没有md5算法的错误
     **/
    void registerUser(User user, String popularize) throws NoSuchAlgorithmException;

}
