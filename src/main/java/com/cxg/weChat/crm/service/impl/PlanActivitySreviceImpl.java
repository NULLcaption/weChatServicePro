package com.cxg.weChat.crm.service.impl;

import com.cxg.weChat.crm.mapper.PlanActivityMapper;
import com.cxg.weChat.crm.pojo.*;
import com.cxg.weChat.crm.service.PlanActivitySrevice;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 获取数据之实现类
 * @Author xg.chen
 * @Date 15:47 2018/11/29
**/
@Transactional
@Service
public class PlanActivitySreviceImpl implements PlanActivitySrevice{

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(PlanActivitySrevice.class);

    @Autowired
    PlanActivityMapper planActivityMapper;

    @Override
    public int updateIdCardZ(PromotionInfoDo promotionInfoDo) {
        try{
            planActivityMapper.updateIdCardZ(promotionInfoDo);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateIdCardF(PromotionInfoDo promotionInfoDo) {
        try{
            planActivityMapper.updateIdCardF(promotionInfoDo);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getPromotionInfoByOpenId(String openId) {
        try{
            return planActivityMapper.getPromotionInfoByOpenId(openId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<StoreDo> getStoreByOpenId(String openId) {
        try{
            return planActivityMapper.getStoreByOpenId(openId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StoreDo> getStoreByCode(String code) {
        try{
            return planActivityMapper.getStoreByCode(code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int findPromotionStore(PromotionStoreDo promotionStoreDo) {
        try{
            return planActivityMapper.promotionStoreDo(promotionStoreDo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteStoreById(PromotionStoreDo promotionStoreDo) {
        try{
            planActivityMapper.deleteStoreById(promotionStoreDo);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<SkuDo> getSkuList(String mark) {
        try {
            return planActivityMapper.getSkuList(mark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PromotionSkuNumDo> getDistributionData(PromotionNumDo promotionNumDo) {
        try{
            return planActivityMapper.getDistributionData(promotionNumDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PromotionSkuNumDo> getInventoryData(PromotionNumKDo promotionNumKDo) {
        try{
            return planActivityMapper.getInventoryData(promotionNumKDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PromotionSkuNumDo> getCompData(PromotionNumZDo promotionNumZDo) {
        try{
            return planActivityMapper.getCompData(promotionNumZDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateSiginByOpenId(SiginDo siginDo) {
        try{
            planActivityMapper.updateSiginByOpenId(siginDo);
            return 1;
        } catch (Exception e) {
            logger.error("更新失败");
        }
        return 0;
    }

    @Override
    public List<SiginDo> getSignInData(SiginDo siginDo) {
        try{
            return planActivityMapper.getSignInData(siginDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SiginDo> getSignOutData(SiginDo siginDo) {
        try{
            return planActivityMapper.getSignOutData(siginDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PromotionInfoDo getPromotionInfo(String openId) {
        try{
            return planActivityMapper.getPromotionInfo(openId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getDistributionCount(PromotionNumDo promotionNumDo) {
        try {
            return planActivityMapper.getDistributionCount(promotionNumDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateDistribution(PromotionNumDo promotionNumDo) {
        try {
            planActivityMapper.updateDistribution(promotionNumDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getInventoryCount(PromotionNumKDo promotionNumKDo) {
        try {
            return planActivityMapper.getInventoryCount(promotionNumKDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateInventory(PromotionNumKDo promotionNumKDo) {
        try {
            planActivityMapper.updateInventory(promotionNumKDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getComplimentaryCount(PromotionNumZDo promotionNumZDo) {
        try{
            return planActivityMapper.getComplimentaryCount(promotionNumZDo);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void updateComplimentary(PromotionNumZDo promotionNumZDo) {
        try {
            planActivityMapper.updateComplimentary(promotionNumZDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PromotionStorePhotoDo> getPhotoData(PromotionStorePhotoDo promotionStorePhotoDo) {
        try{
            return planActivityMapper.getPhotoData(promotionStorePhotoDo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
