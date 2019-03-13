package cn.yu.cartoon.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值记录表的映射对象
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 12:25
 **/
public class DepositRecord {

    private Integer recordId;

    private Integer userId;

    private Date depositTime;

    private BigDecimal depositAmount;

    private String payProvider;

    private String payRecordCode;

    private Integer packageId;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(Date depositTime) {
        this.depositTime = depositTime;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getPayProvider() {
        return payProvider;
    }

    public void setPayProvider(String payProvider) {
        this.payProvider = payProvider;
    }

    public String getPayRecordCode() {
        return payRecordCode;
    }

    public void setPayRecordCode(String payRecordCode) {
        this.payRecordCode = payRecordCode;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }
}
