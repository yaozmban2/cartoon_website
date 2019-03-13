package cn.yu.cartoon.pojo.dto;

import java.math.BigDecimal;

/**
 * 二维码表的映射对象
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 12:31
 **/
public class QRCode {

    private Integer qrCodeId;

    private BigDecimal qrCodeFunds;

    private byte qrCodeProvider;

    private String qrCodeUrl;

    private Integer qrPrice;

    private byte isFreed;

    private byte isDelete;

    public Integer getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(Integer qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public BigDecimal getQrCodeFunds() {
        return qrCodeFunds;
    }

    public void setQrCodeFunds(BigDecimal qrCodeFunds) {
        this.qrCodeFunds = qrCodeFunds;
    }

    public byte getQrCodeProvider() {
        return qrCodeProvider;
    }

    public void setQrCodeProvider(byte qrCodeProvider) {
        this.qrCodeProvider = qrCodeProvider;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public Integer getQrPrice() {
        return qrPrice;
    }

    public void setQrPrice(Integer qrPrice) {
        this.qrPrice = qrPrice;
    }

    public byte getIsFreed() {
        return isFreed;
    }

    public void setIsFreed(byte isFreed) {
        this.isFreed = isFreed;
    }

    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "QRCode{" +
                "qrCodeId=" + qrCodeId +
                ", qrCodeFunds=" + qrCodeFunds +
                ", qrCodeProvider=" + qrCodeProvider +
                ", qrCodeUrl='" + qrCodeUrl + '\'' +
                ", qrPrice=" + qrPrice +
                ", isFreed=" + isFreed +
                ", isDelete=" + isDelete +
                '}';
    }
}
