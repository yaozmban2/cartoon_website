package cn.yu.cartoon.service;

import java.security.NoSuchAlgorithmException;

/**
 * 登录相关的Controller
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/8 22:54
 **/
public interface LoginService {

    /**
     *  登录操作
     *
     * @author Yu
     * @date 22:58 2019/2/8
     * @param userName 用户名
     * @param password 用户密码
     * @return boolean 登录成功返回 true 登录失败返回 false
     **/
    boolean login(String userName, String password) throws NoSuchAlgorithmException;
}
