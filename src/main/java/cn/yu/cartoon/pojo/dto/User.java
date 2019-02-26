package cn.yu.cartoon.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String userPassword;

    private String userEmail;

    private String userPhone;

    private Integer userGold;

    private BigDecimal userRmb;

    private BigDecimal userPromotion;

    private String userPopularize;

    private Byte userGroup;

    private Byte isDelete;

    private Date vipTime;

    private Date createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserGold() {
        return userGold;
    }

    public void setUserGold(Integer userGold) {
        this.userGold = userGold;
    }

    public BigDecimal getUserRmb() {
        return userRmb;
    }

    public void setUserRmb(BigDecimal userRmb) {
        this.userRmb = userRmb;
    }

    public BigDecimal getUserPromotion() {
        return userPromotion;
    }

    public void setUserPromotion(BigDecimal userPromotion) {
        this.userPromotion = userPromotion;
    }

    public String getUserPopularize() {
        return userPopularize;
    }

    public void setUserPopularize(String userPopularize) {
        this.userPopularize = userPopularize;
    }

    public Byte getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(Byte userGroup) {
        this.userGroup = userGroup;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getVipTime() {
        return vipTime;
    }

    public void setVipTime(Date vipTime) {
        this.vipTime = vipTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userGold=" + userGold +
                ", userRmb=" + userRmb +
                ", userPromotion=" + userPromotion +
                ", userPopularize='" + userPopularize + '\'' +
                ", userGroup=" + userGroup +
                ", isDelete=" + isDelete +
                ", vipTime=" + vipTime +
                '}';
    }
}