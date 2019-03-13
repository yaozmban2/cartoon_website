package cn.yu.cartoon.service;

import cn.yu.cartoon.pojo.dto.Combo;

/**
 * 商品套餐相关服务
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 14:28
 **/
public interface ComboService {

    /**
     *  将套餐信息存入数据库中
     *
     * @author Yu
     * @date 14:48 2019/3/1
     * @param combo 套餐对象
     * @return boolean 存入成功返回true  失败返回false
     **/
    boolean setCombo(Combo combo);

    /**
     *  根据套餐的ID获得套餐相关信息
     *
     * @author Yu
     * @date 19:54 2019/3/1
     * @param comboId 套餐ID
     * @return Combo 返回查找到的套餐信息
     **/
    Combo getComboById(Integer comboId);

    /**
     *  检查该用户之前是否已经下过同样的未结订单
     *   如果有则在redis中有相关数据，搜索返回二维码地址
     *
     * @author Yu
     * @date 16:20 2019/3/4
     * @param userId 用户Id
     * @param comboId 套餐Id
     * @return String 如果有返回的是二维码地址，如果没有返回的是null
     **/
    String checkForUnpayOrder(int userId, int comboId);

    /**
     *  从数据库中查找未被使用的二维码
     *  将相关信息存入redis缓存中
     *  返回二维码地址
     *
     * @author Yu
     * @date 16:30 2019/3/4
     * @param comboPrice 套餐的价格
     * @return String 返回二维码的地址
     **/
    String getQRCodeForOrder(int comboPrice);
}
