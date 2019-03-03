package cn.yu.cartoon.dao;

import cn.yu.cartoon.pojo.dto.Combo;

/**
 * 套餐表的操作
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 12:51
 **/
public interface ComboMapper {

    /**
     *  向套餐表中插入一个套餐信息，并且将自增的id写入对象
     *
     * @author Yu
     * @date 15:02 2019/3/1
     * @param combo 套餐对象
     * @return int 成功返回1
     **/
    int insertAndGetId(Combo combo);

    /**
     *  根据套餐ID从数据库中查找到套餐信息
     *
     * @author Yu
     * @date 19:57 2019/3/1
     * @param comboId 套餐ID
     * @return Combo 含有套餐信息的对象
     **/
    Combo selectById(Integer comboId);
}
