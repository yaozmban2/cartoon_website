package cn.yu.cartoon.redis;

import cn.yu.cartoon.config.StaticResource;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/3/3 22:06
 **/
@Repository("qrCodeRedisDao")
public class QrCodeRedisDao {

    private final RedisTemplate redisTemplate;

    @Autowired
    public QrCodeRedisDao(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     *  在redis中搜索 key为 QRCode:combo:{funds} 的数据
     *  该数据存放了哪位用户申请了金额为{funds}的二维码
     *
     * @author Yu
     * @date 22:18 2019/3/3
     * @param funds 二维码的金额
     * @return HashMap<String, String>
     **/
    public HashMap<String, String> selectQrCodeWithCombo(String funds) {

        String recordKey = MessageFormat.format("QRCode:combo:{0}", funds);
        return (HashMap) redisTemplate.opsForHash().entries(recordKey);

    }

    /**
     *  往redis中插入key为 QRCode:combo:{funds} 的数据
     *  该数据存放了哪位用户申请了金额为{funds}的二维码
     *
     * @author Yu
     * @date 22:55 2019/3/3
     * @param funds 二维码的金额
     * @param userId 用户的id
     * @param comboId 套餐的id
     * @param comboType 套餐的优惠类型
     * @param qrCodeId 二维码的ID
     **/
    public void insertQrCodeWithCombo(BigDecimal funds, int userId, int comboId, byte comboType, int qrCodeId) {
        String recordKey = MessageFormat.format("QRCode:combo:{0}", String.valueOf(funds));
        Map<String, String> recordMap = new HashMap<>(4);
        recordMap.put("userId", String.valueOf(userId));
        recordMap.put("comboId", String.valueOf(comboId));
        recordMap.put("comboType", String.valueOf(comboType));
        recordMap.put("qrCodeId", String.valueOf(qrCodeId));
        recordMap.put("createTime", new DateTime().toString(StaticResource.getFormatStr()));
        redisTemplate.opsForHash().putAll(recordKey, recordMap);
    }

}
