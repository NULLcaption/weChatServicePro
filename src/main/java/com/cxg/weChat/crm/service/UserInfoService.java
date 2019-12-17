package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description:    后端服务接口
* @Author:         Cheney Master
* @CreateDate:     2018/11/5 10:20
* @Version:        1.0
*/
@Service
public interface UserInfoService {

    UserInfoDo getUserInfoById(int i);

    int checkAdminRole(String code);

    List<SkuDo> getSkuList();

    PromotionInfoDo getPromotionInfoByLoginId(String username);

    int createSiginInInfo(SiginDo siginDo);

    int createSiginOutInfo(SiginDo siginDo);

    int createPromotionNum(PromotionNumDo promotionNumDo);

    int getPromotionNumById(PromotionNumDo promotionNumDo);

    List<PromotionNumDo> getPromotionNumList(PromotionNumDo promotionNumDo);

    int updateSiginPhotoUrl(SiginDo siginDo);

    int updateSiginOutPhotoUrl(SiginDo siginDo);

}
