package com.cxg.weChat.crm.pojo;

import java.io.Serializable;

/**
 * @Description 提报人提报门店数量之实体列
 * @Author xg.chen
 * @Date 12:44 2019/12/12
 **/

public class PromotionSkuNumDo implements Serializable{

    private String openid;
    private String storeId;
    private String skuId;//skuId
    private String skuName;//产品名称
    private String num;//单位杯数
    private String unit;//计量单位
    private String subDate;
    private String custName;//门店名称

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
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

    @Override
    public String toString() {
        return "PromotionSkuNumDo{" +
                "openid='" + openid + '\'' +
                ", storeId='" + storeId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", skuName='" + skuName + '\'' +
                ", num='" + num + '\'' +
                ", unit='" + unit + '\'' +
                ", subDate='" + subDate + '\'' +
                '}';
    }
}
