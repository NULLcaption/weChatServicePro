package com.cxg.weChat.crm.controller;

import com.alibaba.fastjson.JSON;
import com.cxg.weChat.core.config.Constant;
import com.cxg.weChat.core.shiro.RedisCache;
import com.cxg.weChat.core.shiro.RedisCacheManager;
import com.cxg.weChat.core.utils.*;
import com.cxg.weChat.crm.pojo.*;
import com.cxg.weChat.crm.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 春节大战之微信小程序
 * @Author: Cheney Master
 * @CreateDate: 2018/10/30 11:39
 * @Version: 1.0
 */

@Controller
@RequestMapping("/api/wx/pro")
@Validated
public class WeChatPromotionController {
    private static Logger logger = LoggerFactory.getLogger(WeChatPromotionController.class);

    @Autowired
    PlanActivitySrevice planActivitySrevice;

    @Autowired
    HttpSession session;

    @Autowired
    PromotionInfoService promotionInfoService;

    @Autowired
    StoreService storeService;

    @Autowired
    PromotionStoreService promotionStoreService;

    @Autowired
    PromotionNumService promotionNumService;

    @Autowired
    PromotionNumZService promotionNumZService;

    @Autowired
    PromotionNumKService promotionNumKService;

    @Autowired
    PromotionPhotoService promotionPhotoService;

    @Autowired
    SiginService siginService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ValueOperations<String, Object> valueOperations;


    /**
     * @Description: 通过java后台获取微信的相关的信息
     * @Author: Cheney Master
     * @CreateDate: 2018/10/30 13:44
     * @Version: 1.0
     */
    @RequestMapping("/weChatInfo")
    @ResponseBody
    public Map<String, Object> getSession(String code) {
        return this.getSessionByCode(code);
    }

    /**
     * @Description: 通过java后台获取函数
     * @Author: Cheney Master
     * @CreateDate: 2018/10/30 13:30
     * @Version: 1.0
     */
    private Map<String, Object> getSessionByCode(String code) {
        String url = Constant.requestUrl + "?appid="
                + Constant.appid_l + "&secret="
                + Constant.appSercret_l
                + "&js_code=" + code
                + "&grant_type=" + Constant.grant_type;
        //http发送请求
        String data = HttpUtil.get(url);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = null;
        try {
            json = mapper.readValue(data, Map.class);
            logger.debug("返回的json数据{}：", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 用户基本信息注册
     *
     * @param openId
     * @param userName
     * @param sex
     * @param mobile
     * @param idCard
     * @return
     */
    @PostMapping("/submitInfo")
    @ResponseBody
    public R insertPromotionInfo(@NotBlank(message = "openId不能为空")String openId,
                                 String userName,
                                 String sex, String mobile,
                                 String idCard, String role,
                                 String nickName, String avatarUrl) {
        //先根据openID判断下是不是已经注册过了
        PromotionInfoDo promotionInfoDo1 = planActivitySrevice.getPromotionInfoByOpenId(openId);
        //没有注册，那么久保存注册信息
        if (promotionInfoDo1 == null) {
            PromotionInfoDo promotionInfoDo = new PromotionInfoDo();
            promotionInfoDo.setOpenId(openId);
            promotionInfoDo.setUserName(userName);
            promotionInfoDo.setSex(sex);
            promotionInfoDo.setMobile(mobile);
            promotionInfoDo.setIdCard(idCard);
            promotionInfoDo.setUserRole(role);
            promotionInfoDo.setNickName(nickName);
            promotionInfoDo.setAvatarUrl(avatarUrl);
            promotionInfoDo.setCreateDate(DateUtils.dateToStr(new Date()));
            promotionInfoDo.setStatus("Y");
            //保存至数据库
            PoolSendDataUtil poolSendDataUtil = new PoolSendDataUtil();
            poolSendDataUtil.send(() -> promotionInfoService.save(promotionInfoDo));
            poolSendDataUtil.close();
        }

        return R.ok();
    }

    /**
     * 用户省份证正面上传
     *
     * @param openId
     * @param index
     * @return
     */
    @PostMapping("/idCardZ")
    @ResponseBody
    public R uploadIdCardZ(HttpServletRequest request,
                           @NotBlank(message = "openId不能为空")String openId,
                           String index) {
        String flagFile = uploadUtils.uploadImageFiles(request, openId, index);
        if (flagFile.equals("error")) {
            return R.error();
        } else {
            PromotionInfoDo promotionInfoDo = new PromotionInfoDo();
            promotionInfoDo.setOpenId(openId);
            promotionInfoDo.setPicH(flagFile);
            int count = planActivitySrevice.updateIdCardZ(promotionInfoDo);
            if (count == 0) {
                return R.error();
            }
        }
        return R.ok();
    }

    /**
     * 用户身份证反面上传
     *
     * @param openId
     * @param storeId
     * @param index
     * @return
     */
    @PostMapping("/idCardF")
    @ResponseBody
    public R uploadIdCardF(HttpServletRequest request,
                           @NotBlank(message = "openId不能为空")String openId,
                           String storeId,
                           String index) {
        String flagFile = uploadUtils.uploadImageFiles(request, openId, index);
        if (flagFile.equals("error")) {
            return R.error();
        } else {
            PromotionInfoDo promotionInfoDo = new PromotionInfoDo();
            promotionInfoDo.setOpenId(openId);
            promotionInfoDo.setPicG(flagFile);
            int count = planActivitySrevice.updateIdCardF(promotionInfoDo);
            if (count == 0) {
                return R.error();
            }
        }
        return R.ok();
    }

    /**
     * 检查用户是否注册
     *
     * @param openId
     * @return
     */
    @PostMapping("/check")
    @ResponseBody
    public R checkPromotionInfo(@NotBlank(message = "openId不能为空")String openId) {
        PromotionInfoDo promotionInfoDo = planActivitySrevice.getPromotionInfoByOpenId(openId);
        //检查用户没有注册
        if (promotionInfoDo == null) {
            return R.error();
        }
        //用户注册了，但是强制退出，没有上传身份证照片，需要再次上传
        if (StringUtils.isEmpty(promotionInfoDo.getPicG()) ||
                StringUtils.isEmpty(promotionInfoDo.getPicH())) {
            return R.error(2, "没有上传身份证照片");
        }
        //人员离职，没有操作权限
        if(promotionInfoDo.getStatus().equals("N")) {
            return R.error(3, "人员离职，没有操作权限");
        }
        return R.ok();
    }

    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     */
    @GetMapping("/getPromotionInfo")
    @ResponseBody
    public String getPromotionInfo(@NotBlank(message = "openId不能为空")String openId) {
        //先去redis中找，没有就去数据库中找，然后放到redis中
        PromotionInfoDo promotionInfoDo = (PromotionInfoDo) valueOperations.get("promotionInfoDo_" + openId);
        if (promotionInfoDo == null) {
            promotionInfoDo = planActivitySrevice.getPromotionInfo(openId);
            //数据库取取出来为空的处理
            if (promotionInfoDo == null) {
                return JSONUtils.beanToJson(promotionInfoDo);
            }
            if (promotionInfoDo.getSex().equals("0")) {
                promotionInfoDo.setSex("男");
            }
            if (promotionInfoDo.getSex().equals("1")) {
                promotionInfoDo.setSex("男");
            }
            if (promotionInfoDo.getSex().equals("2")) {
                promotionInfoDo.setSex("女");
            }
            if (promotionInfoDo.getUserRole().equals("0")) {
                promotionInfoDo.setUserRole("促销员");
            }
            if (promotionInfoDo.getUserRole().equals("1")) {
                promotionInfoDo.setUserRole("促销员");
            }
            if (promotionInfoDo.getUserRole().equals("1")) {
                promotionInfoDo.setUserRole("理货员");
            }
            if (promotionInfoDo.getUserRole().equals("2")) {
                promotionInfoDo.setUserRole("督导");
            }
            //将用户用户信息放到redis中
            if (promotionInfoDo != null) {
                valueOperations.set("promotionInfoDo_" + openId, promotionInfoDo, Constant.KEY_TIME, TimeUnit.SECONDS);
            }
        } else {
            if (promotionInfoDo.getSex().equals("0")) {
                promotionInfoDo.setSex("男");
            }
            if (promotionInfoDo.getSex().equals("1")) {
                promotionInfoDo.setSex("女");
            }
            if (promotionInfoDo.getUserRole().equals("0")) {
                promotionInfoDo.setUserRole("促销员");
            }
            if (promotionInfoDo.getUserRole().equals("1")) {
                promotionInfoDo.setUserRole("理货员");
            }
            if (promotionInfoDo.getUserRole().equals("2")) {
                promotionInfoDo.setUserRole("督导");
            }
        }
        return JSONUtils.beanToJson(promotionInfoDo);
    }

    /**
     * 获取维护的门店列表
     *
     * @param openId
     * @return
     */
    @GetMapping("/getStoreList")
    @ResponseBody
    public String getStoreList(@NotBlank(message = "openId不能为空")String openId) throws InterruptedException {
        //获取门店列表
        List<StoreDo> storeDoList = planActivitySrevice.getStoreByOpenId(openId);
        String json = JSONUtils.beanToJson(storeDoList);
        return json;
    }

    /**
     * 通过生成唯一码获取门店
     *
     * @param code
     * @return
     */
    @GetMapping("/getSrotre")
    @ResponseBody
    public String getSrotre(String code) {
        List<StoreDo> storeDoList = planActivitySrevice.getStoreByCode(code);
        return JSONUtils.beanToJson(storeDoList);
    }

    /**
     * 添加门店
     * 同一个人同一家门店只能绑定一次不能多次绑定
     *
     * @param list
     * @param openId
     * @return
     */
    @PostMapping("/addStore")
    @ResponseBody
    public R addStore(String list, @NotBlank(message = "openId不能为空")String openId) {
        List<StoreDo> storeDoList = JSON.parseArray(list, StoreDo.class);
        if (storeDoList != null) {
            PromotionStoreDo promotionStoreDo = new PromotionStoreDo();
            promotionStoreDo.setOpenId(openId);
            promotionStoreDo.setStoreId(String.valueOf(storeDoList.get(0).getId()));
            promotionStoreDo.setStatus("Y");
            promotionStoreDo.setCreateDate(DateUtils.dateToStr(new Date()));
            int count = planActivitySrevice.findPromotionStore(promotionStoreDo);
            if (count >= 1) {
                return R.error(2, "门店已绑定");
            }
            //保存绑定门店
            PoolSendDataUtil poolSendDataUtil = new PoolSendDataUtil();
            poolSendDataUtil.send(() -> promotionStoreService.save(promotionStoreDo));
            poolSendDataUtil.close();
        }
        return R.ok();
    }

    /**
     * 用户解绑门店
     *
     * @param storeId
     * @param openId
     * @return
     */
    @GetMapping("/deleteStore")
    @ResponseBody
    public R deleteStore(@NotBlank(message = "门店Id不能为空")String storeId,
                         @NotBlank(message = "openId不能为空")String openId) {
        PromotionStoreDo promotionStoreDo = new PromotionStoreDo();
        promotionStoreDo.setOpenId(openId);
        promotionStoreDo.setStoreId(storeId);
        int count = planActivitySrevice.deleteStoreById(promotionStoreDo);
        if (count == 0) {
            return R.error(2, "解绑失败");
        }
        return R.ok();
    }

    /**
     * 签到
     *
     * @param address
     * @param date 签到时间直接取当前的服务器时间
     * @param openId
     * @param storeId
     * @return
     */
    @PostMapping("/signin")
    @ResponseBody
    public R signin(HttpServletRequest request, String address,
                    String date,
                    @NotBlank(message = "openId不能为空")String openId,
                    @NotBlank(message = "门店Id不能为空")String storeId,
                    String index) {
        String flagFile = uploadUtils.uploadImageFiles(request, openId, index);
        if (flagFile.equals("error")) {
            return R.error();
        } else {
            SiginDo siginDo = new SiginDo();
            siginDo.setOpenid(openId);
            siginDo.setStoreId(storeId);
            siginDo.setSignInAddress(address);
            siginDo.setSignInTime(DateUtils.dateToStr(new Date()));
            siginDo.setStatus("I");
            siginDo.setPhotoUrl(flagFile);
            //线程异步写入数据库
            PoolSendDataUtil poolSendDataUtil = new PoolSendDataUtil();
            poolSendDataUtil.send(() -> siginService.save(siginDo));
            poolSendDataUtil.close();
        }
        return R.ok();
    }

    /**
     * 获取签到数据
     *
     * @param openId
     * @return
     */
    @GetMapping("/getSignInData")
    @ResponseBody
    public String getSignInData(HttpServletRequest request,
                                @NotBlank(message = "openId不能为空")String openId) {
        SiginDo siginDo = new SiginDo();
        siginDo.setOpenid(openId);
        List<SiginDo> list = planActivitySrevice.getSignInData(siginDo);
        if (list != null) {
            for (SiginDo siginDo1 : list) {
                siginDo1.setPhotoUrl("https://" + request.getServerName() + ":" + request.getServerPort() + ("/images/" + siginDo1.getPhotoUrl().replace("/files/", "")));
            }
        }
        return JSONUtils.beanToJson(list);
    }

    /**
     * 签退
     *
     * @param address
     * @param date
     * @param openId
     * @param storeId
     * @return
     */
    @PostMapping("/signout")
    @ResponseBody
    public R signout(HttpServletRequest request, String address,
                     String date,
                     @NotBlank(message = "openId不能为空")String openId,
                     @NotBlank(message = "门店Id不能为空")String storeId,
                     String index) {
        String flagFile = uploadUtils.uploadImageFiles(request, openId, index);
        if (flagFile.equals("error")) {
            return R.error();
        } else {
            SiginDo siginDo = new SiginDo();
            siginDo.setOpenid(openId);
            siginDo.setStoreId(storeId);
            siginDo.setSignInAddress(address);
            siginDo.setSignInTime(DateUtils.dateToStr(new Date()));
            siginDo.setStatus("O");
            siginDo.setPhotoUrl(flagFile);
            //线程异步写入数据库
            PoolSendDataUtil poolSendDataUtil = new PoolSendDataUtil();
            poolSendDataUtil.send(() -> siginService.save(siginDo));
            poolSendDataUtil.close();
        }
        return R.ok();
    }

    /**
     * 获取签退数据
     *
     * @param openId
     * @return
     */
    @GetMapping("/getSignOutData")
    @ResponseBody
    public String getSignOutData(HttpServletRequest request,
                                 @NotBlank(message = "openId不能为空")String openId) {
        SiginDo siginDo = new SiginDo();
        siginDo.setOpenid(openId);
        List<SiginDo> list = planActivitySrevice.getSignOutData(siginDo);
        if (list != null) {
            for (SiginDo siginDo1 : list) {
                siginDo1.setPhotoUrl("https://" + request.getServerName() + ":" + request.getServerPort() + ("/images/" + siginDo1.getPhotoUrl().replace("/files/", "")));
            }
        }
        return JSONUtils.beanToJson(list);
    }

    /**
     * 获取参加活动的品项
     *
     * @return
     */
    @PostMapping("/getSku")
    @ResponseBody
    public String getSku(String mark) {
        //redis中获取
        //这里的redis的key值是Object_id的key，保证多数据的唯一性
        //并设置缓存的过期时间，到达和数据库中的数据的一致性
        String skuDos;
        if ("Z".equals(mark)) {//赠品
            skuDos = (String) valueOperations.get("skuDoListZ");
            if (skuDos == null) {
                List<SkuDo> skuDoList = planActivitySrevice.getSkuList(mark);
                valueOperations.set("skuDoListZ", JSONUtils.beanToJson(skuDoList), Constant.KEY_TIME, TimeUnit.SECONDS);
                return JSONUtils.beanToJson(skuDoList);
            }
        } else {//销售产品
            skuDos = (String) valueOperations.get("skuDoList");
            if (skuDos == null) {
                List<SkuDo> skuDoList = planActivitySrevice.getSkuList(mark);
                valueOperations.set("skuDoList", JSONUtils.beanToJson(skuDoList), Constant.KEY_TIME, TimeUnit.SECONDS);
                return JSONUtils.beanToJson(skuDoList);
            }
        }
        return skuDos;
    }

    /**
     * 提报销量数据
     *
     * @param openId
     * @param storeId
     * @param list
     * @return
     */
    @PostMapping("/subDistribution")
    @ResponseBody
    public R subDistribution(@NotBlank(message = "openId不能为空")String openId,
                             @NotBlank(message = "门店Id不能为空")String storeId,
                             String list, String dates) {
        //这里去更新数据库的时候可以使用多线程的方式异步的去提交
        PoolSendDataUtil poolSendDataUtil = new PoolSendDataUtil();
        poolSendDataUtil.send(() -> saveDistribution(openId, storeId, list, dates));
        poolSendDataUtil.close();
        return R.ok();
    }

    /**
     * 保存数据库
     *
     * @param openId 人员Id
     * @param storeId 门店Id
     * @param list 提报SKU
     * @param dates 提报时间
     */
    private void saveDistribution(@NotBlank(message = "openId不能为空")String openId,
                                  @NotBlank(message = "门店Id不能为空")String storeId,
                                  String list, String dates) {
        List<SkuDo> skuDoList = JSON.parseArray(list, SkuDo.class);
        List<PromotionNumDo> promotionNumDos = new ArrayList<>();
        for (SkuDo skuDo : skuDoList) {
            PromotionNumDo promotionNumDo = new PromotionNumDo();
            promotionNumDo.setOpenid(openId);
            promotionNumDo.setStoreId(storeId);
            promotionNumDo.setSkuId(String.valueOf(skuDo.getId()));
            if (skuDo.getNum() == null || StringUtils.isEmpty(skuDo.getNum().trim())) {
                continue;
            }
            promotionNumDo.setNum(skuDo.getNum());
            promotionNumDo.setUnit(skuDo.getUnit());
            promotionNumDo.setSubDate(dates);
            promotionNumDo.setCreateDate(DateUtils.dateToStr(new Date()));
            //一个人一个门店一天只能提报一次，其余为修改
            int count = planActivitySrevice.getDistributionCount(promotionNumDo);
            if (count == 1) {
                planActivitySrevice.updateDistribution(promotionNumDo);
                continue;
            }
            promotionNumDos.add(promotionNumDo);
        }
        promotionNumService.saveAll(promotionNumDos);
    }

    /**
     * 获取已经提报的数据
     *
     * @param openId
     * @param storeId
     * @return
     */
    @GetMapping("/getDistributionData")
    @ResponseBody
    public String getDistributionData(@NotBlank(message = "openId不能为空")String openId,
                                      @NotBlank(message = "门店Id不能为空")String storeId) {
        PromotionNumDo promotionNumDo = new PromotionNumDo();
        promotionNumDo.setOpenid(openId);
        promotionNumDo.setStoreId(storeId);
        List<PromotionSkuNumDo> promotionSkuNumDos = planActivitySrevice.getDistributionData(promotionNumDo);
        return JSONUtils.beanToJson(promotionSkuNumDos);
    }

    /**
     * 库存提报
     *
     * @param openId
     * @param storeId
     * @param list
     * @return
     */
    @PostMapping("/subInventory")
    @ResponseBody
    public R subInventory(@NotBlank(message = "openId不能为空")String openId,
                          @NotBlank(message = "门店Id不能为空")String storeId,
                          String list, String dates) {
        //这里去更新数据库的时候可以使用多线程的方式异步的去提交
        PoolSendDataUtil poolSendDataUtil = new PoolSendDataUtil();
        poolSendDataUtil.send(() -> saveInventory(openId, storeId, list, dates));
        poolSendDataUtil.close();
        return R.ok();
    }

    /**
     * 保存库存至数据库
     *
     * @param openId
     * @param storeId
     * @param list
     */
    private void saveInventory(String openId, String storeId, String list, String dates) {
        List<SkuDo> skuDoList = JSON.parseArray(list, SkuDo.class);
        List<PromotionNumKDo> promotionNumKDos = new ArrayList<>();
        for (SkuDo skuDo : skuDoList) {
            PromotionNumKDo promotionNumKDo = new PromotionNumKDo();
            promotionNumKDo.setOpenid(openId);
            promotionNumKDo.setStoreId(storeId);
            promotionNumKDo.setSkuId(String.valueOf(skuDo.getId()));
            if (skuDo.getNum() == null || StringUtils.isEmpty(skuDo.getNum().trim())) {
                continue;
            }
            promotionNumKDo.setNum(skuDo.getNum());
            promotionNumKDo.setUnit(skuDo.getUnit());
            promotionNumKDo.setSubDate(dates);
            promotionNumKDo.setCreateDate(DateUtils.dateToStr(new Date()));
            //一个人一个门店一个品项一天只能提报一次，其余为修改
            int count = planActivitySrevice.getInventoryCount(promotionNumKDo);
            if (count == 1) {
                planActivitySrevice.updateInventory(promotionNumKDo);
                continue;
            }

            promotionNumKDos.add(promotionNumKDo);
        }
        promotionNumKService.saveAll(promotionNumKDos);
    }

    /**
     * 获取提报库存数据
     *
     * @return
     */
    @GetMapping("/getInventoryData")
    @ResponseBody
    public String getInventoryData(@NotBlank(message = "openId不能为空")String openId,
                                   @NotBlank(message = "门店Id不能为空")String storeId) {
        PromotionNumKDo promotionNumKDo = new PromotionNumKDo();
        promotionNumKDo.setOpenid(openId);
        promotionNumKDo.setStoreId(storeId);
        List<PromotionSkuNumDo> promotionSkuNumDos = planActivitySrevice.getInventoryData(promotionNumKDo);
        return JSONUtils.beanToJson(promotionSkuNumDos);
    }


    /**
     * 提报赠品
     *
     * @param openId
     * @param storeId
     * @param list
     * @return
     */
    @PostMapping("/subComplimentary")
    @ResponseBody
    public R subComplimentary(@NotBlank(message = "openId不能为空")String openId,
                              @NotBlank(message = "门店Id不能为空")String storeId,
                              String list, String dates) {
        //这里去更新数据库的时候可以使用多线程的方式异步的去提交
        PoolSendDataUtil poolSendDataUtil = new PoolSendDataUtil();
        poolSendDataUtil.send(() -> saveComplimentary(openId, storeId, list, dates));
        poolSendDataUtil.close();
        return R.ok();
    }

    /**
     * 保存数据至数据库
     *
     * @param openId
     * @param storeId
     * @param list
     */
    private void saveComplimentary(@NotBlank(message = "openId不能为空")String openId,
                                   @NotBlank(message = "门店Id不能为空")String storeId,
                                   String list, String dates) {
        List<SkuDo> skuDoList = JSON.parseArray(list, SkuDo.class);
        List<PromotionNumZDo> promotionNumZDos = new ArrayList<>();
        for (SkuDo skuDo : skuDoList) {
            PromotionNumZDo promotionNumZDo = new PromotionNumZDo();
            promotionNumZDo.setOpenid(openId);
            promotionNumZDo.setStoreId(storeId);
            promotionNumZDo.setSkuId(String.valueOf(skuDo.getId()));
            if (skuDo.getNum() == null || StringUtils.isEmpty(skuDo.getNum().trim())) {
                continue;
            }
            promotionNumZDo.setNum(skuDo.getNum());
            promotionNumZDo.setUnit(skuDo.getUnit());
            promotionNumZDo.setSubDate(dates);
            promotionNumZDo.setCreateDate(DateUtils.dateToStr(new Date()));
            //一个人一个门店一天只能提报一次，其余为修改
            int count = planActivitySrevice.getComplimentaryCount(promotionNumZDo);
            if (count == 1) {
                planActivitySrevice.updateComplimentary(promotionNumZDo);
                continue;
            }

            promotionNumZDos.add(promotionNumZDo);
        }
        promotionNumZService.saveAll(promotionNumZDos);
    }

    /**
     * 获取提报的赠品数量
     *
     * @param openId
     * @return
     */
    @GetMapping("/getCompData")
    @ResponseBody
    public String getCompData(@NotBlank(message = "openId不能为空")String openId,
                              @NotBlank(message = "门店Id不能为空")String storeId) {
        PromotionNumZDo promotionNumZDo = new PromotionNumZDo();
        promotionNumZDo.setOpenid(openId);
        promotionNumZDo.setStoreId(storeId);
        List<PromotionSkuNumDo> promotionSkuNumDos = planActivitySrevice.getCompData(promotionNumZDo);
        return JSONUtils.beanToJson(promotionSkuNumDos);
    }

    /**
     * 上传现场图片
     *
     * @param openId
     * @param storeId
     * @param index
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public R upload(HttpServletRequest request,@NotBlank(message = "openId不能为空")String openId,
                    @NotBlank(message = "门店Id不能为空")String storeId, String index) {
        String flagFile = uploadUtils.uploadImageFiles(request, openId, index);
        if (flagFile.equals("error")) {
            return R.error();
        } else {
            PromotionPhotoDo promotionPhotoDo = new PromotionPhotoDo();
            promotionPhotoDo.setOpenid(openId);
            promotionPhotoDo.setStoreId(storeId);
            promotionPhotoDo.setSubDate(DateUtils.dateToStr(new Date()));
            promotionPhotoDo.setPicUrl(flagFile);

            PoolSendDataUtil poolSendDataUtil = new PoolSendDataUtil();
            poolSendDataUtil.send(() -> promotionPhotoService.save(promotionPhotoDo));
            poolSendDataUtil.close();
        }
        return R.ok();
    }

    /**
     * 获取门店的拍照数据
     *
     * @param openId
     * @param storeId
     * @return
     */
    @GetMapping("/getPhotoData")
    @ResponseBody
    public String getPhotoData(HttpServletRequest request, @NotBlank(message = "openId不能为空")String openId,
                               @NotBlank(message = "门店Id不能为空")String storeId) {
        PromotionStorePhotoDo promotionStorePhotoDo = new PromotionStorePhotoDo();
        promotionStorePhotoDo.setOpenid(openId);
        promotionStorePhotoDo.setStoreId(storeId);
        List<PromotionStorePhotoDo> promotionStorePhotoDoList = planActivitySrevice.getPhotoData(promotionStorePhotoDo);
        if (promotionStorePhotoDoList != null) {
            for (PromotionStorePhotoDo promotionStorePhotoDo1 : promotionStorePhotoDoList) {
                promotionStorePhotoDo1.setPicUrl("https://" + request.getServerName() + ":" + request.getServerPort() + ("/images/" + promotionStorePhotoDo1.getPicUrl().replace("/files/", "")));
            }
        }
        return JSONUtils.beanToJson(promotionStorePhotoDoList);
    }

    /**
     * 促销员扫码活动
     *
     * @param openId 获取促销员的信息
     * @return
     */
    @GetMapping("/redPacket")
    @ResponseBody
    public String promoterPlan(@NotBlank(message = "openId不能为空")String openId) throws Exception {
        if(openId.equals("null") || openId.equals("") || openId.equals("undefined")){
            return "1";
        }
        //通过openid获取促销员信息
        //先去redis中找，没有就去数据库中找，然后放到redis中
        PromotionInfoDo promotionInfoDo = (PromotionInfoDo) valueOperations.get("promotionInfoDo_" + openId);
        if (promotionInfoDo == null) {
            promotionInfoDo = planActivitySrevice.getPromotionInfo(openId);
        }
        //保证缓存和数据中获取的对象都不为空
        if(promotionInfoDo !=  null){
            //用户注册了，但是强制退出，没有上传身份证照片，需要再次上传
            if (StringUtils.isEmpty(promotionInfoDo.getPicG()) ||
                    StringUtils.isEmpty(promotionInfoDo.getPicH())) {
                return "2";
            }
            //人员离职，没有操作权限
            if(promotionInfoDo.getStatus().equals("N")) {
                return "3";
            }
            //生成二维码接口参数
            Long id = promotionInfoDo.getId();//人员ID
            String name = promotionInfoDo.getUserName();//姓名
            Long time = System.currentTimeMillis();//时间戳
            String secret = EncryptUtil.getAESString(String.valueOf(id) + String.valueOf(time));//加密字串
            //请求API接口参数
            //https://xpp.happydoit.com/api/promoter?id=1&name=2&time=666666&secret=gCrh4C7iPLI=
            String url = "https://xpp.happydoit.com/api/promoter"
                    +"?id="+ id
                    +"&name="+name
                    +"&time="+time
                    +"&secret="+secret;
            logger.error("HttpRestful url:" + url);
            return url;
        }
        return "1";
    }

}
