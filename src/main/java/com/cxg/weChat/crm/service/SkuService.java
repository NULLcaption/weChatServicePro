package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.SkuDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 品项维护服务类
 * @Author xg.chen
 * @Date 19:33 2019/12/10
 **/

public interface SkuService extends JpaRepository<SkuDo, Long>{
}
