package com.cxg.weChat.crm.service;

import com.cxg.weChat.crm.pojo.PromotionPhotoDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description 图片上传服务类
 * @Author xg.chen
 * @Date 20:18 2019/12/10
 **/
public interface PromotionPhotoService extends JpaRepository<PromotionPhotoDo, Long>{

}
