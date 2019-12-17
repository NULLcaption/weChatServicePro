package com.cxg.weChat.core.utils;

import com.cxg.weChat.core.config.BootdoConfig;
import com.cxg.weChat.core.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Description 上传文件工具类
 * @Author xg.chen
 * @Date 10:26 2019/4/26
 **/

public class uploadUtils {

    public static String uploadImageFiles(HttpServletRequest request, String id, String index) {
        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        MultipartFile multipartFile =  req.getFile("file");
        //技术问题
        String realPath = Constant.upload_url;
        String imageUrl;
        try {
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            long time = System.currentTimeMillis();
            imageUrl  = "/files/"+id+"_"+index+"_"+ String.valueOf(time)+".jpg";
            File file  =  new File(realPath,id+"_"+index+"_"+ String.valueOf(time)+".jpg");
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "error";
        }
        return imageUrl;
    }
}
