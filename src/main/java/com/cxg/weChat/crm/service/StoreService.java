package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.StoreDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 门店维护服务
 * @Author xg.chen
 * @Date 20:33 2019/12/10
 **/
public interface StoreService extends JpaRepository<StoreDo, Long>{
}
