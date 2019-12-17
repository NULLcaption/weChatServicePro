package com.cxg.weChat.crm.service.impl;

import com.cxg.weChat.crm.mapper.UserInfoMapper;
import com.cxg.weChat.crm.pojo.*;
import com.cxg.weChat.crm.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @Description:    服务类接口实现类
* @Author:         Cheney Master
* @CreateDate:     2018/11/5 10:25
* @Version:        1.0
*/

@Transactional
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfoDo getUserInfoById(int id) {
        return userInfoMapper.getUserInfoById(id);
    }

    @Override
    public int checkAdminRole(String code) {
        try {
            return userInfoMapper.checkAdminRole(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public List<SkuDo> getSkuList() {
        try {
            return userInfoMapper.getSkuList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PromotionInfoDo getPromotionInfoByLoginId(String username) {
        try {
            return userInfoMapper.getPromotionInfoByLoginId(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int createSiginInInfo(SiginDo siginDo) {
        try {
            userInfoMapper.createSiginInInfo(siginDo);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int createSiginOutInfo(SiginDo siginDo) {
        try {
            userInfoMapper.createSiginOutInfo(siginDo);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int createPromotionNum(PromotionNumDo promotionNumDo) {
        try {
            userInfoMapper.createPromotionNum(promotionNumDo);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int getPromotionNumById(PromotionNumDo promotionNumDo) {
        try {
            return userInfoMapper.getPromotionNumById(promotionNumDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<PromotionNumDo> getPromotionNumList(PromotionNumDo promotionNumDo) {
        try {
            return userInfoMapper.getPromotionNumList(promotionNumDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateSiginPhotoUrl(SiginDo siginDo) {
        try {
            userInfoMapper.updateSiginPhotoUrl(siginDo);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateSiginOutPhotoUrl(SiginDo siginDo) {
        try {
            userInfoMapper.updateSiginOutPhotoUrl(siginDo);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
