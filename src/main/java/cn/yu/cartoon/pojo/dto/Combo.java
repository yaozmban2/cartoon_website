package cn.yu.cartoon.pojo.dto;

/**
 * package表的映射对象
 *
 * @author Yu
 * @version 1.0
 * @date 2019/3/1 12:21
 **/
public class Combo {

    private Integer comboId;

    private Integer comboPrice;

    private Integer comboCurrency;

    private Integer vipDay;

    private byte comboType;

    private byte isDelete;

    public Integer getComboId() {
        return comboId;
    }

    public void setComboId(Integer comboId) {
        this.comboId = comboId;
    }

    public Integer getComboPrice() {
        return comboPrice;
    }

    public void setComboPrice(Integer comboPrice) {
        this.comboPrice = comboPrice;
    }

    public Integer getComboCurrency() {
        return comboCurrency;
    }

    public void setComboCurrency(Integer comboCurrency) {
        this.comboCurrency = comboCurrency;
    }

    public Integer getVipDay() {
        return vipDay;
    }

    public void setVipDay(Integer vipDay) {
        this.vipDay = vipDay;
    }

    public byte getComboType() {
        return comboType;
    }

    public void setComboType(byte comboType) {
        this.comboType = comboType;
    }

    public byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(byte isDelete) {
        this.isDelete = isDelete;
    }
}
