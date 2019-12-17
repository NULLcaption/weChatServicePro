package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.PromotionInfoDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @Description 人员服务类
 * @Author xg.chen
 * @Date 20:13 2019/12/10
 **/
public interface PromotionInfoService extends JpaRepository<PromotionInfoDo, Long>{
}
