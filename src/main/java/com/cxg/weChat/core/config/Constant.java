package com.cxg.weChat.core.config;

public class Constant {
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS ="redis";
    public static String LOG_ERROR = "error";

    public static String grant_type = "authorization_code";
    public static String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
    //兰芳园
    public static String appid_l = "";
    public static String appSercret_l = "";
    //香飘飘
    public static String appid_x = "";
    public static String appSercret_x = "";
    //meco
    public static String appid_m = "";
    public static String appSercret_m = "";
    //促销小程序
    public static String appid_c = "";
    public static String appSercret_c = "";


    public static String upload_url = "/var/uploaded_files/promotion";
    public static String upload_url_t = "D:/var/uploaded_files/promotion";


    
}
