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
}
