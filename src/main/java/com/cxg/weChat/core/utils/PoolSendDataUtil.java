package com.cxg.weChat.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @Description 线程池
 * @Author xg.chen
 * @Date 11:13 2019/12/13
**/

public class PoolSendDataUtil {
    private static final Logger logger = LoggerFactory.getLogger(PoolSendDataUtil.class);
    BlockingQueue<Runnable> workQueue;//任务队列
    ExecutorService executorService;//线程池接口

    //构造线程方法
    public PoolSendDataUtil(){
        //构造无界的任务队列，资源足够，理论可以支持无限个任务
        workQueue = new LinkedBlockingQueue<>();
        executorService = new ThreadPoolExecutor(2, 10, 30,
                TimeUnit.SECONDS, workQueue,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    //将任务放到线程池中
    public void send(Runnable task){
        logger.debug("Runnable task running...");
        executorService.execute(task);
    }
    //关闭线程池
    public void close(){
        executorService.shutdown();
    }
}
