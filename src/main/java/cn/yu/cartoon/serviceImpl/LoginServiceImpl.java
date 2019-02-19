package cn.yu.cartoon.serviceImpl;

import cn.yu.cartoon.dao.UserMapper;
import cn.yu.cartoon.pojo.dto.User;
import cn.yu.cartoon.service.LoginService;
import cn.yu.cartoon.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/8 23:02
 **/
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) throws NoSuchAlgorithmException {
        User user = userMapper.selectUserByUserName(userName);
        return Encryption.md5(password).equals(user.getUserPassword());
    }
}
