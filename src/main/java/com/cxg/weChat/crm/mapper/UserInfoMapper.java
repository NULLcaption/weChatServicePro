package com.cxg.weChat.crm.mapper;

import com.cxg.weChat.crm.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
* @Description:
* @Author:         Cheney Master
* @CreateDate:     2018/11/5 10:27
* @Version:        1.0
*/

@Mapper
public interface UserInfoMapper {

    UserInfoDo getUserInfoById(int id);

    int checkAdminRole(String code);

    List<SkuDo> getSkuList();

    PromotionInfoDo getPromotionInfoByLoginId(String username);

    void createSiginInInfo(SiginDo siginDo);

    void createSiginOutInfo(SiginDo siginDo);

    void createPromotionNum(PromotionNumDo promotionNumDo);

    int getPromotionNumById(PromotionNumDo promotionNumDo);

    List<PromotionNumDo> getPromotionNumList(PromotionNumDo promotionNumDo);

    void updateSiginPhotoUrl(SiginDo siginDo);

    void updateSiginOutPhotoUrl(SiginDo siginDo);
}
