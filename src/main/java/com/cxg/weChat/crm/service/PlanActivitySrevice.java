package com.cxg.weChat.crm.service;

import com.cxg.weChat.core.utils.Query;
import com.cxg.weChat.crm.pojo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 获取数据之接口
 * @Author xg.chen
 * @Date 15:45 2018/11/29
**/
@Service
public interface PlanActivitySrevice {

    int updateIdCardZ(PromotionInfoDo promotionInfoDo);

    int updateIdCardF(PromotionInfoDo promotionInfoDo);

    int getPromotionInfoByOpenId(String openId);

    List<StoreDo> getStoreByOpenId(String openId);

    List<StoreDo> getStoreByCode(String code);

    int findPromotionStore(PromotionStoreDo promotionStoreDo);

    int deleteStoreById(PromotionStoreDo promotionStoreDo);

    List<SkuDo> getSkuList(String mark);

    List<PromotionSkuNumDo> getDistributionData(PromotionNumDo promotionNumDo);

    List<PromotionSkuNumDo> getInventoryData(PromotionNumKDo promotionNumkDo);

    List<PromotionSkuNumDo> getCompData(PromotionNumZDo promotionNumZDo);

    int updateSiginByOpenId(SiginDo siginDo);

    List<SiginDo> getSignInData(SiginDo siginDo);

    List<SiginDo> getSignOutData(SiginDo siginDo);

    PromotionInfoDo getPromotionInfo(String openId);

    int getDistributionCount(PromotionNumDo promotionNumDo);

    void updateDistribution(PromotionNumDo promotionNumDo);

    int getInventoryCount(PromotionNumKDo promotionNumKDo);

    void updateInventory(PromotionNumKDo promotionNumKDo);

    int getComplimentaryCount(PromotionNumZDo promotionNumZDo);

    void updateComplimentary(PromotionNumZDo promotionNumZDo);

    List<PromotionStorePhotoDo> getPhotoData(PromotionStorePhotoDo promotionStorePhotoDo);
}
