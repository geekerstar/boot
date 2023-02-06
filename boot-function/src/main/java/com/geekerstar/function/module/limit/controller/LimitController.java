package com.geekerstar.function.module.limit.controller;

import com.geekerstar.function.config.log.Weblog;
import com.geekerstar.function.config.web.Response;
import com.geekerstar.function.exception.BusinessException;
import com.geekerstar.function.module.limit.service.case1.CounterLimiter;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.google.common.util.concurrent.RateLimiter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2023/2/6 14:46
 */
@Slf4j
@RestController
@RequestMapping("/limit")
@Api(tags = "限流")
@RequiredArgsConstructor
@ApiSupport(author = "Geekerstar", order = 1)
public class LimitController {

    private final CounterLimiter counterLimiter;

    /**
     * 每秒钟可以创建两个令牌
     */
    RateLimiter limiter = RateLimiter.create(2.0);

    @Weblog(description = "限流测试")
    @GetMapping("/test")
    @ApiOperation(value = "限流测试")
    @ApiOperationSupport(author = "Geekerstar", order = 1)
    public Response<String> test() {
        if (!counterLimiter.tryAcquire()) {
            throw new BusinessException("达到限流上限");
        }
        return Response.success();
    }

    /**
     * Guava RateLimiter客户端限流 开始
     * https://mp.weixin.qq.com/s/La5qCJh5Vup_gZIgZMOo4g
     */

    @Weblog(description = "非阻塞限流")
    @GetMapping("/tryAcquire")
    @ApiOperation(value = "非阻塞限流")
    @ApiOperationSupport(author = "Geekerstar", order = 2)
    public String tryAcquire(Integer count) {
        //count 每次消耗的令牌
        if (limiter.tryAcquire(count)) {
            log.info("成功，允许通过，速率为{}", limiter.getRate());
            return "success";
        } else {
            log.info("错误，不允许通过，速率为{}", limiter.getRate());
            return "fail";
        }
    }

    @Weblog(description = "限定时间的非阻塞限流")
    @GetMapping("/tryAcquireWithTimeout")
    @ApiOperation(value = "限定时间的非阻塞限流")
    @ApiOperationSupport(author = "Geekerstar", order = 3)
    public String tryAcquireWithTimeout(Integer count, Integer timeout) {
        //count 每次消耗的令牌  timeout 超时等待的时间
        if (limiter.tryAcquire(count, timeout, TimeUnit.SECONDS)) {
            log.info("成功，允许通过，速率为{}", limiter.getRate());
            return "success";
        } else {
            log.info("错误，不允许通过，速率为{}", limiter.getRate());
            return "fail";
        }
    }

    @Weblog(description = "同步阻塞限流")
    @GetMapping("/acquire")
    @ApiOperation(value = "同步阻塞限流")
    @ApiOperationSupport(author = "Geekerstar", order = 5)
    public String acquire(Integer count) {
        limiter.acquire(count);
        log.info("成功，允许通过，速率为{}", limiter.getRate());
        return "success";
    }

    /**
     * Guava RateLimiter客户端限流 结束
     */
}
