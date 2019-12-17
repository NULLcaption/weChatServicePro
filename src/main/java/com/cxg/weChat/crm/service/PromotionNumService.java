package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.PromotionNumDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 分销提报服务类
 * @Author xg.chen
 * @Date 20:14 2019/12/10
 **/
public interface PromotionNumService extends JpaRepository<PromotionNumDo, Long>{
}
