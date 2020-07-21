package com.cxg.weChat.crm.pojo;

import com.cxg.weChat.crm.controller.LoginController;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description 促销员用户
 *
 * @Author xg.chen
 * @Date 13:11 2019/4/24
 **/
@Entity
@Table(name = "MARKET_TB_PROMOTION_INFO",schema = "MARKET")
public class PromotionInfoDo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proInfoSeq")
    @SequenceGenerator(name = "proInfoSeq", initialValue = 1, allocationSize = 1, sequenceName = "MARKET.MARKET_SEQ_PROMOTION_INFO")
    private Long id;//id
    @Column(name = "OPEN_ID", updatable=false, unique=true)
    private String openId;//openid
    @Column(name = "USER_NAME")
    private String userName;//姓名
    @Column(name = "SEX")
    private String sex;//性别：男1；女2
    @Column(name = "MOBILE")
    private String mobile;//手机号
    @Column(name = "ID_CARD")
    private String idCard;//身份证号
    @Column(name = "USER_ROLE")
    private String userRole;//人员类型：促销员1，理货员2，导购督导3
    @Column(name = "CREATE_DATE")
    private String createDate;//创建时间
    @Column(name = "nick_name")
    private String nickName;//微信昵称
    @Column(name = "avatar_url")
    private String avatarUrl;//微信头像
    @Column(name = "pic_h")
    private String picH;//身份证头像面
    @Column(name = "pic_g")
    private String picG;//身份证国徽面
    @Column(name = "status")
    private String status;//在职离职状态: Y:在职  N：离职

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPicH() {
        return picH;
    }

    public void setPicH(String picH) {
        this.picH = picH;
    }

    public String getPicG() {
        return picG;
    }

    public void setPicG(String picG) {
        this.picG = picG;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
