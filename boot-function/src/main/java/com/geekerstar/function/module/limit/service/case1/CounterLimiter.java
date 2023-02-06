package com.geekerstar.function.module.limit.service.case1;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author geekerstar
 * @date 2023/2/6 14:46
 *
 * 计数器限流，不推荐
 */
@Service
public class CounterLimiter {

    //初始时间
    private static long startTime = System.currentTimeMillis();

    //初始计数值
    private static final AtomicInteger ZERO = new AtomicInteger(0);

    //时间窗口限制
    private static final long interval = 1000;

    //限制通过请求
    private static int limit = 2;

    //请求计数
    private AtomicInteger requestCount = ZERO;

    //获取限流
    public boolean tryAcquire() {

        long now = System.currentTimeMillis();

        //在时间窗口内
        if (now < startTime + interval) {

            //判断是否超过最大请求
            if (requestCount.get() < limit) {
                requestCount.incrementAndGet();
                return true;
            }
            return false;

        } else {

            //超时重置
            startTime = now;
            requestCount = ZERO;
            return true;
        }

    }
}
