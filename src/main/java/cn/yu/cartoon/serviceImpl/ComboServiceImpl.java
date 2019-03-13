package cn.yu.cartoon.serviceImpl;

import cn.yu.cartoon.dao.ComboMapper;
import cn.yu.cartoon.dao.QRCodeMapper;
import cn.yu.cartoon.pojo.dto.Combo;
import cn.yu.cartoon.redis.ComboRedisDao;
import cn.yu.cartoon.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品套餐相关服务
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 14:28
 **/
@Service("comboService")
public class ComboServiceImpl implements ComboService {

    private final ComboMapper comboMapper;

    private final ComboRedisDao comboRedisDao;

    private final QRCodeMapper qrCodeMapper;

    @Autowired
    public ComboServiceImpl(ComboMapper comboMapper, ComboRedisDao comboRedisDao, QRCodeMapper qrCodeMapper) {

        this.comboMapper = comboMapper;
        this.comboRedisDao = comboRedisDao;
        this.qrCodeMapper = qrCodeMapper;
    }

    @Override
    public boolean setCombo(Combo combo) {

        combo.setIsDelete((byte)0);

        return 1 == comboMapper.insertAndGetId(combo);
    }

    @Override
    public Combo getComboById(Integer comboId) {
        return comboMapper.selectById(comboId);
    }

    @Override
    public String checkForUnpayOrder(int userId, int comboId) {

        return comboRedisDao.selectUserWithCombo(userId, comboId);
    }

    @Override
    public String getQRCodeForOrder(int comboPrice) {

        return null;
    }
}
