package com.cxg.weChat.crm.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description SKU实体类
 *
 * @Author xg.chen
 * @Date 13:33 2019/4/2
**/
@Entity
@Table(name = "MARKET_TB_PROMOTION_SKU", schema = "MARKET")
public class SkuDo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skuSeq")
    @SequenceGenerator(name = "skuSeq", initialValue = 1, allocationSize = 1, sequenceName = "MARKET.MARKET_SEQ_PROMOTION_SKU")
    private Long id;

    @Column(name = "sku_id")
    private String skuId;//skuId

    @Column(name = "sku_name")
    private String skuName;//产品名称

    @Column(name = "brand")
    private String brand;//类别:销售产品:X;促销赠品:Z

    private String status;//状态:正常:Y;已禁用:N

    @Column(name = "num")
    private String num;//单位杯数

    private String nums;//单标箱杯数

    @Column(name = "unit")
    private String unit;//计量单位

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SkuDo{" +
                "id=" + id +
                ", skuId='" + skuId + '\'' +
                ", skuName='" + skuName + '\'' +
                ", brand='" + brand + '\'' +
                ", status='" + status + '\'' +
                ", num='" + num + '\'' +
                ", nums='" + nums + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
