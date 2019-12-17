package com.cxg.weChat.crm.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 促销员对应的门店
 * @Author xg.chen
 * @Date 14:19 2019/12/10
 **/
@Entity
@Table(name = "MARKET_TB_PROMOTION_STORE", schema = "MARKET")
public class PromotionStoreDo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proStoInfoSeq")
    @SequenceGenerator(name = "proStoInfoSeq", initialValue = 1, allocationSize = 1, sequenceName = "MARKET.MARKET_SEQ_PROMOTION_STORE")
    private Long id;
    @Column(name = "OPENID", nullable = false)
    @NotNull(message = "openId不能为空")
    private String openId;
    @Column(name = "STORE_ID", nullable = false)
    @NotNull(message = "storeId不能为空")
    private String storeId;
    @Column(name = "status")
    private String status;//状态：Y绑定，N解绑
    @Column(name = "CREATE_DATE")
    private String createDate;//创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "PromotionStoreDo{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", status='" + status + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
