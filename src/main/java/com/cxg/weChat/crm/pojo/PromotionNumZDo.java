package com.cxg.weChat.crm.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description 促销员赠品提报实体
 *
 * @Author xg.chen
 * @Date 14:15 2019/4/25
 **/
@Entity
@Table(name = "MARKET_TB_PROMOTION_NUMZ", schema = "MARKET")
public class PromotionNumZDo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proNumZSeq")
    @SequenceGenerator(name = "proNumZSeq", initialValue = 1, allocationSize = 1, sequenceName = "MARKET.MARKET_SEQ_PROMOTION_NUMZ")
    private Long id;
    @Column(name = "OPENID")
    private String openid;
    @Column(name = "STORE_ID")
    private String storeId;
    @Column(name = "SKU_ID")
    private String skuId;
    @Column(name = "NUMZ")
    private String num;
    @Column(name = "UNIT")
    private String unit;
    @Column(name = "SUB_DATE")
    private String subDate;
    @Column(name = "CREATE_DATE")
    private String createDate;

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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "PromotionNumDo{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", storeId='" + storeId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", num='" + num + '\'' +
                ", unit='" + unit + '\'' +
                ", subDate='" + subDate + '\'' +
                '}';
    }
}
