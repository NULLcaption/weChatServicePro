package com.cxg.weChat.crm.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description 签到签退实体类
 *
 * @Author xg.chen
 * @Date 10:05 2019/4/25
 **/
@Entity
@Table(name = "MARKET_TB_PROMOTION_SIGN_IN", schema = "MARKET")
public class SiginDo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "signSeq")
    @SequenceGenerator(name = "signSeq", initialValue = 1, allocationSize = 1, sequenceName = "MARKET.MARKET_SEQ_PROMOTION_SIGN_IN")
    private Long id;
    @Column(name = "OPENID")
    private String openid;
    @Column(name = "STORE_ID")
    private String storeId;
    @Column(name = "SIGN_IN_TIME")
    private String signInTime;
    @Column(name = "SIGN_IN_ADDRESS")
    private String signInAddress;
    @Column(name = "PHOTO_URL")
    private String photoUrl;
    @Column(name = "status")
    private String status;//区分签到签退：签到I，签退O

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(String signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignInAddress() {
        return signInAddress;
    }

    public void setSignInAddress(String signInAddress) {
        this.signInAddress = signInAddress;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }


}
