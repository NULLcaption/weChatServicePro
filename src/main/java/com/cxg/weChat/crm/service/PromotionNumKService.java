package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.PromotionNumKDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 库存提报服务类
 * @Author xg.chen
 * @Date 20:16 2019/12/10
 **/
public interface PromotionNumKService extends JpaRepository<PromotionNumKDo, Long> {
}
