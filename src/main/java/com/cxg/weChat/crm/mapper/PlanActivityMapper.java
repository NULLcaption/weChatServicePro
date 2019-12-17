package com.cxg.weChat.crm.mapper;

import com.cxg.weChat.crm.pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description 获取获取数据
 * @Author xg.chen
 * @Date 15:50 2018/11/29
**/
@Mapper
public interface PlanActivityMapper {

    void updateIdCardZ(PromotionInfoDo promotionInfoDo);

    void updateIdCardF(PromotionInfoDo promotionInfoDo);

    int getPromotionInfoByOpenId(String openId);

    List<StoreDo> getStoreByOpenId(String openId);

    List<StoreDo> getStoreByCode(String code);

    int promotionStoreDo(PromotionStoreDo promotionStoreDo);

    void deleteStoreById(PromotionStoreDo promotionStoreDo);

    List<SkuDo> getSkuList(String mark);

    List<PromotionSkuNumDo> getDistributionData(PromotionNumDo promotionNumDo);

    List<PromotionSkuNumDo> getInventoryData(PromotionNumKDo promotionNumKDo);

    List<PromotionSkuNumDo> getCompData(PromotionNumZDo promotionNumZDo);

    void updateSiginByOpenId(SiginDo siginDo);

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
