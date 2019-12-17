package com.cxg.weChat.crm.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description 现场图片实体
 * @Author xg.chen
 * @Date 14:14 2019/12/10
 **/
@Entity
@Table(name = "MARKET_TB_PROMOTION_PHOTO",schema = "MARKET")
public class PromotionPhotoDo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proPhoSeq")
    @SequenceGenerator(name = "proPhoSeq", initialValue = 1, allocationSize = 1, sequenceName = "MARKET.MARKET_SEQ_PROMOTION_NUM")
    private Long id;
    @Column(name = "OPENID")
    private String openid;
    @Column(name = "STORE_ID")
    private String storeId;
    @Column(name = "pic_url")
    private String picUrl;
    @Column(name = "SUB_DATE")
    private String subDate;

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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    @Override
    public String toString() {
        return "PromotionPhotoDo{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", storeId='" + storeId + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", subDate='" + subDate + '\'' +
                '}';
    }
}
