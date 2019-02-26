package cn.yu.cartoon.dao;

import cn.yu.cartoon.pojo.dto.Popularize;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/23 16:00
 **/
public interface PopularizeMapper {

    /**
     *  插入到推广表中
     *
     * @author Yu
     * @date 16:22 2019/2/23
     * @param popularize 推广表的数据
     **/
    void insert(Popularize popularize);

}
