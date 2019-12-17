package com.cxg.weChat.crm.pojo;

import java.io.Serializable;

/**
 * @Description 门店照片值实体类实体
 * @Author xg.chen
 * @Date 9:53 2019/12/14
**/

public class PromotionStorePhotoDo implements Serializable{

    private String openid;//openid
    private String storeId;//门店Id
    private String custName;//门店名称
    private String picUrl;//照片路径
    private String subDate;//拍照时间

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

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
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
}
