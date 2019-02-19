package cn.yu.cartoon.serviceImpl;

import cn.yu.cartoon.dao.UserMapper;
import cn.yu.cartoon.pojo.dto.User;
import cn.yu.cartoon.service.RegisterService;
import cn.yu.cartoon.utils.Encryption;
import cn.yu.cartoon.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/7 15:28
 **/
@Service(value = "registerService")
public class RegisterServiceImpl implements RegisterService {

    private static Pattern NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z][\\u4e00-\\u9fa5a-zA-Z]+$");
    private static Pattern PASSWORD_PATTERN = Pattern.compile("[0-9]{1,}");
    private static Pattern EMAIL_PATTERN = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private static final int POPULARIZE_STRING_LENGTH = 6;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean isLegalName(String username) {
        int illegalNameLength = 20;
        if (username.length() > illegalNameLength) {
            return false;
        } else {
            Matcher m = NAME_PATTERN.matcher(username);
            return m.matches();
        }
    }

    @Override
    public int isLegalPassword(String password, String validatePassword) {
        int easyPasswordLength = 6;
        if (password.length() < easyPasswordLength) {
            return 1;
        }
        Matcher m = PASSWORD_PATTERN.matcher(password);
        boolean result=m.matches();
        if (result) {
            return 1;
        }
        if (!password.equals(validatePassword)) {
            return 2;
        }

        return 0;
    }

    @Override
    public boolean isLegalEmail(String email) {
        Matcher m = EMAIL_PATTERN.matcher(email);
        return m.matches();
    }

    @Override
    public boolean validateUserName(String userName) {
        return null == userMapper.selectUserByUserName(userName);
    }

    @Override
    public boolean validateUserEmail(String userEmail) {
        return null == userMapper.selectUserByUserEmail(userEmail);
    }


    @Override
    public void registerUser(User user, String popularize) throws NoSuchAlgorithmException {
        //设置用户密码加密
        user.setUserPassword(Encryption.md5(user.getUserPassword()));
        user.setUserGold(0);
        user.setUserRmb(BigDecimal.valueOf(0));
        user.setUserPromotion(BigDecimal.valueOf(0));
        //设置用户为普通用户
        Byte a = 2;
        user.setUserGroup(a);
        //设置用户的推广代码
        String userPopularize = RandomUtils.randomString(POPULARIZE_STRING_LENGTH);
        while (null != userMapper.selectUserByUserPopularize(userPopularize)) {
            userPopularize = RandomUtils.randomString(POPULARIZE_STRING_LENGTH);
        }
        user.setUserPopularize(userPopularize);
        //设置上家信息
        if (popularize != null && !("").equals(popularize)) {
            User popularizeUser = userMapper.selectUserByUserPopularize(popularize);
            user.setPreviousContact(popularizeUser.getUserId());
        }
        //设置创建时间
        user.setCreateTime(new Date());
        user.setVipTime(new Date());
        userMapper.insert(user);
    }
}
