package cn.yu.cartoon.pojo.dto;

/**
 * Popularize表的映射数据类
 *
 * @author Yu
 * @version 1.0
 * @date 2019/2/23 16:00
 **/
public class Popularize {

    private Integer popularizeId;

    private Integer inviteesId;

    private Byte inviteLevel;

    private Integer previousId;

    public Integer getPopularizeId() {
        return popularizeId;
    }

    public void setPopularizeId(Integer popularizeId) {
        this.popularizeId = popularizeId;
    }

    public Integer getInviteesId() {
        return inviteesId;
    }

    public void setInviteesId(Integer inviteesId) {
        this.inviteesId = inviteesId;
    }

    public Byte getInviteLevel() {
        return inviteLevel;
    }

    public void setInviteLevel(Byte inviteLevel) {
        this.inviteLevel = inviteLevel;
    }

    public Integer getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Integer previousId) {
        this.previousId = previousId;
    }

    @Override
    public String toString() {
        return "Popularize{" +
                "popularizeId=" + popularizeId +
                ", inviteesId=" + inviteesId +
                ", inviteLevel=" + inviteLevel +
                ", previousId=" + previousId +
                '}';
    }
}
