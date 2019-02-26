package cn.yu.cartoon.serviceImpl;

import cn.yu.cartoon.dao.PopularizeMapper;
import cn.yu.cartoon.dao.UserMapper;
import cn.yu.cartoon.pojo.dto.Popularize;
import cn.yu.cartoon.pojo.dto.User;
import cn.yu.cartoon.service.RegisterService;
import cn.yu.cartoon.utils.Encryption;
import cn.yu.cartoon.utils.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

    private static Pattern NAME_PATTERN = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z][\\u4e00-\\u9fa5a-zA-Z1-9]+$");
    private static Pattern PASSWORD_PATTERN = Pattern.compile("[0-9]{1,}");
    private static Pattern EMAIL_PATTERN = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    private static final int POPULARIZE_STRING_LENGTH = 6;

    private final UserMapper userMapper;
    private final PopularizeMapper popularizeMapper;

    @Autowired
    public RegisterServiceImpl(PopularizeMapper popularizeMapper, UserMapper userMapper) {
        this.popularizeMapper = popularizeMapper;
        this.userMapper = userMapper;
    }

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
    public int checkRegisterInfo(User user, String validatePassword) {

        final int tooEasyPassword = 1;
        final int differentPassword = 2;

        //判断用户名是否合法
        if (!isLegalName(user.getUserName())) {
            return 1;
        }
        //判断用户名是否已经存在
        if (!validateUserName(user.getUserName())) {
            return 2;
        }
        //判断密码是否合法
        int validatePasswordResult = isLegalPassword(user.getUserPassword(), validatePassword);
        if (tooEasyPassword == validatePasswordResult) {
            return 3;
        }
        if (differentPassword == validatePasswordResult) {
            return 4;
        }
        //判断邮箱是否合法
        if (!isLegalEmail(user.getUserEmail())) {
            return 5;
        }
        //判断用户邮箱是否已存在
        if (!validateUserEmail(user.getUserEmail())) {
            return 6;
        }
        return 0;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int registerUser(User user, String validatePassword, HttpServletRequest request){

        final int registerInfoNoProblem = 0;

        //检查用户的注册信息是否有效
        if (registerInfoNoProblem != checkRegisterInfo(user, validatePassword)) {
            return checkRegisterInfo(user, validatePassword);
        }
        //设置用户密码加密
        try {
            user.setUserPassword(Encryption.md5(user.getUserPassword()));
        } catch (NoSuchAlgorithmException e) {
            logger.warn("line:133 ，没有md5算法", e);
            return 7;
        }
        user.setUserGold(0);
        user.setUserRmb(BigDecimal.valueOf(0));
        user.setUserPromotion(BigDecimal.valueOf(0));
        //设置用户为普通用户
        user.setUserGroup((byte)2);
        //设置创建时间
        user.setCreateTime(new Date());
        user.setVipTime(new Date());
        //设置用户的推广代码
        String userPopularize = RandomUtils.randomString(POPULARIZE_STRING_LENGTH);
        while (null != userMapper.selectUserByUserPopularize(userPopularize)) {
            userPopularize = RandomUtils.randomString(POPULARIZE_STRING_LENGTH);
        }
        user.setUserPopularize(userPopularize);
        //判断是否有推广
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie:cookies) {
                if ("popularize".equals(cookie.getName())) {
                    User popularizeUser = getUserByPopularizedCode(cookie.getValue());
                    if (null != popularizeUser) {
                        setPopularize(popularizeUser.getUserId(), user.getUserId(), (byte)1);
                    }
                }
            }
        }
        userMapper.insert(user);

        return 0;
    }

    @Override
    public User getUserByPopularizedCode(String popularizedCode) {
        //先查看redis看是否有记录
        return userMapper.selectUserByUserPopularize(popularizedCode);
    }

    @Override
    public void setPopularize(int previousId, int inviteeId, Byte level) {
        Popularize popularize = new Popularize();
        popularize.setPreviousId(previousId);
        popularize.setInviteesId(inviteeId);
        popularize.setInviteLevel(level);
        popularizeMapper.insert(popularize);
    }
}
