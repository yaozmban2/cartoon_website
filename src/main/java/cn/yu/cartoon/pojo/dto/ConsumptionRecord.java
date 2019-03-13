package cn.yu.cartoon.pojo.dto;

import java.util.Date;

/**
 * 消费记录表的映射对象
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 12:29
 **/
public class ConsumptionRecord {

    private Integer consumptionId;

    private Integer userId;

    private Date consumptionTime;

    private byte consumptionType;

    private Integer packageId;

    public Integer getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(Integer consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getConsumptionTime() {
        return consumptionTime;
    }

    public void setConsumptionTime(Date consumptionTime) {
        this.consumptionTime = consumptionTime;
    }

    public byte getConsumptionType() {
        return consumptionType;
    }

    public void setConsumptionType(byte consumptionType) {
        this.consumptionType = consumptionType;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
}
