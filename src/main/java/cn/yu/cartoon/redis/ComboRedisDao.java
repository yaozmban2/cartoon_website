package cn.yu.cartoon.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/4 15:53
 **/
@Repository("comboRedisDao")
public class ComboRedisDao {

    private RedisTemplate redisTemplate;

    @Autowired
    public ComboRedisDao(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /** 
     *  往redis中插入key为 user:combo:{userId}:{comboId} 的数据
     *  该数据使用String结构存放了二维码的图片地址
     *
     * @author Yu
     * @date 15:54 2019/3/4
     * @param userId 用户ID
     * @param comboId 套餐ID
     * @param qrCodeUrl 二维码地址
     **/
    public void insertUserWithCombo(int userId, int comboId, String qrCodeUrl) {

        String key = MessageFormat.format("user:combo:{0}:{1}", userId, comboId);

        redisTemplate.opsForValue().append(key, qrCodeUrl);
    }

    /**
     *  在redis中搜索 key为 user:combo:{userId}:{comboId} 的数据
     *  该数据使用String结构存放了二维码的图片地址
     *
     * @author Yu
     * @date 16:07 2019/3/4
     * @param userId 用户ID
     * @param comboId 套餐ID
     * @return String 二维码的地址，如果没有相关数据会返回null
     **/
    public String selectUserWithCombo(int userId, int comboId) {

        String key = MessageFormat.format("user:combo:{0}:{1}", userId, comboId);

        return (String) redisTemplate.opsForValue().get(key);
    }

}
