package com.cxg.weChat.crm.controller;

import com.cxg.weChat.core.utils.MD5Utils;
import com.cxg.weChat.core.utils.R;
import com.cxg.weChat.crm.pojo.UserInfoDo;
import com.cxg.weChat.crm.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
* @Description:    用户登录以及获取用户信息控制类
* @Author:         Cheney Master
* @CreateDate:     2018/11/13 14:05
* @Version:        1.0
*/
@Controller
public class LoginController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserInfoService userInfoService;

    /**
    * @Description:    登录页面跳转链接
    * @Author:         Cheney Master
    * @CreateDate:     2018/11/13 14:17
    * @Version:        1.0
    */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 用户登录登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R ajaxLogin(@RequestParam("username")String username, @RequestParam("password")String password) {
        //密码MD5加密处理
        try {
            password = MD5Utils.md5Encry(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //验证用户名是存在
        //根据登录人账密生成token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //根据每个请求，创建一个Subject,
        //并保存到ThreadContext的resources(ThreadLocal<Map<Object, Object>>)变量中，
        //也就是一个http请求一个subject,并绑定到当前线程。
        Subject subject = SecurityUtils.getSubject();
        try {
            //设置登录以后的token，每次登录后检查登录用户
            //其本质就是依赖于浏览器的cookie来维护session的
            //扩展：如果不是web容器的app,如何实现实现无状态的会话怎么是现实呢？
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    /**
    * @Description:    系统首页
    * @Author:         Cheney Master
    * @CreateDate:     2018/11/14 10:03
    * @Version:        1.0
    */
    @GetMapping("/index")
    public String index(Model model) {
        UserInfoDo userInfoDo = userInfoService.getUserInfoById(Integer.valueOf(getUserId()));
        model.addAttribute("userId",userInfoDo.getUserId());
        model.addAttribute("userName",userInfoDo.getUserName());
        model.addAttribute("phone",userInfoDo.getMobile());
        model.addAttribute("company",userInfoDo.getCompany());
        model.addAttribute("email",userInfoDo.getEmail());
        return "index";
    }
}
