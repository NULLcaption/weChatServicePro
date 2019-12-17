package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.SiginDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 签到签退服务类
 * @Author xg.chen
 * @Date 20:12 2019/12/10
 **/
public interface SiginService extends JpaRepository<SiginDo, Long>{
}
