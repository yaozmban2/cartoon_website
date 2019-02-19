package cn.yu.cartoon.dao;

import cn.yu.cartoon.pojo.dto.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectUserByUserName(String userName);

    User selectUserByUserEmail(String userEmail);

    User selectUserByUserPhone(String userPhone);

    User selectUserByUserPopularize(String userPopularize);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}