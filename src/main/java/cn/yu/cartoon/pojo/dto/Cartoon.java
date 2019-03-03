package cn.yu.cartoon.pojo.dto;

import java.util.Date;

/**
 * @author Yu
 * @version 1.0
 * @date 2019/2/17 16:06
 **/
public class Cartoon {

    private Integer cartoonId;

    private String cartoonName;

    private String cartoonAuthor;

    private Byte cartoonCountry;

    private Byte cartoonType;

    private String cartoonUri;

    private Date cartoonUploadTime;

    public Integer getCartoonId() {
        return cartoonId;
    }

    public void setCartoonId(Integer cartoonId) {
        this.cartoonId = cartoonId;
    }

    public String getCartoonName() {
        return cartoonName;
    }

    public void setCartoonName(String cartoonName) {
        this.cartoonName = cartoonName;
    }

    public String getCartoonAuthor() {
        return cartoonAuthor;
    }

    public void setCartoonAuthor(String cartoonAuthor) {
        this.cartoonAuthor = cartoonAuthor;
    }

    public Byte getCartoonCountry() {
        return cartoonCountry;
    }

    public void setCartoonCountry(Byte cartoonCountry) {
        this.cartoonCountry = cartoonCountry;
    }

    public Byte getCartoonType() {
        return cartoonType;
    }

    public void setCartoonType(Byte cartoonType) {
        this.cartoonType = cartoonType;
    }

    public String getCartoonUri() {
        return cartoonUri;
    }

    public void setCartoonUri(String cartoonUri) {
        this.cartoonUri = cartoonUri;
    }

    public Date getCartoonUploadTime() {
        return cartoonUploadTime;
    }

    public void setCartoonUploadTime(Date cartoonUploadTime) {
        this.cartoonUploadTime = cartoonUploadTime;
    }
}
