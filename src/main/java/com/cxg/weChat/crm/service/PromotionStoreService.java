package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.PromotionStoreDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 人员门店关系服务类
 * @Author xg.chen
 * @Date 20:20 2019/12/10
 **/
public interface PromotionStoreService extends JpaRepository<PromotionStoreDo, Long>{
}
