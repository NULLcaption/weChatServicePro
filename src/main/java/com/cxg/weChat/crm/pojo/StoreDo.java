package com.cxg.weChat.crm.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 门店实体
 * @Author xg.chen
 * @Date 14:38 2019/12/10
 **/
@Entity
@Table(name = "MARKET_TB_STORE", schema = "MARKET")
public class StoreDo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storeSeq")
    @SequenceGenerator(name = "storeSeq", initialValue = 1, allocationSize = 1, sequenceName = "MARKET.MARKET_SEQ_STORE")
    @Column(nullable = false)
    @NotNull(message="STORE ID 不能为空")
    private Long id;
    @Column(name = "saf_code")
    private String safCode;
    @Column(name = "cust_id")
    private String custId;
    @Column(name = "kunnr_id")
    private String kunnrId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "plan_title")
    private String planTitle;
    @Column(name = "plan_date")
        private String planDate;
    @Column(name = "rand_num")
    private String randNum;
    @Column(name = "CREATE_DATE")
    private String createDate;//创建时间
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "end_date")
    private String endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSafCode() {
        return safCode;
    }

    public void setSafCode(String safCode) {
        this.safCode = safCode;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getKunnrId() {
        return kunnrId;
    }

    public void setKunnrId(String kunnrId) {
        this.kunnrId = kunnrId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getRandNum() {
        return randNum;
    }

    public void setRandNum(String randNum) {
        this.randNum = randNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StoreDo{" +
                "id=" + id +
                ", safCode='" + safCode + '\'' +
                ", custId='" + custId + '\'' +
                ", kunnrId='" + kunnrId + '\'' +
                ", custName='" + custName + '\'' +
                ", planTitle='" + planTitle + '\'' +
                ", planDate='" + planDate + '\'' +
                ", randNum='" + randNum + '\'' +
                ", createDate='" + createDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
