package com.geekerstar.function.module.limit.service.case1;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author geekerstar
 * @date 2023/2/6 15:00
 *
 * 基于Guava的令牌桶
 */
public class GuavaRateLimiter {

    public static void main(String[] args) {

        //允许10个，permitsPerSecond
        RateLimiter limiter = RateLimiter.create(2);

        for(int i=1;i<20;i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (limiter.tryAcquire()){
                System.out.println("第"+i+"次请求成功");
            }else{
                System.out.println("第"+i+"次请求拒绝");
            }
        }
    }
}
