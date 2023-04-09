package com.geekerstar.function.module.repeat;

import cn.hutool.http.server.HttpServerRequest;
import com.geekerstar.function.config.log.Weblog;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author geekerstar
 * @date 2023/4/9 16:23
 * https://mp.weixin.qq.com/s?__biz=MzU3MDAzNDg1MA==&mid=2247516332&idx=1&sn=64ea49a423c9976f5f29e916aa7e30b0&chksm=fcf75f61cb80d67705babd7dce0ea407276ced1786921fc1189e51f3ab75369982173ac96c58&mpshare=1&scene=24&srcid=0805h4MfnMLXyZz0KuR4M26g&sharer_sharetime=1659656073320&sharer_shareid=dd84ab6e1860ff58b44d9896f34487b8#rd
 */
@Slf4j
@RestController
@RequestMapping("/repeat")
@Api(tags = "请求去重")
@RequiredArgsConstructor
@ApiSupport(author = "Geekerstar", order = 1)
public class RepeatController {

    private final StringRedisTemplate stringRedisTemplate;

    @Weblog(description = "请求去重")
    @GetMapping("/doTest")
    @ApiOperation(value = "请求去重")
    @ApiOperationSupport(author = "Geekerstar", order = 1)
    public Boolean test(HttpServerRequest httpServerRequest) {
        String userId = "12345678";//用户
        String method = "pay";//接口名
        String dedupMD5 = new ReqRepeatHelper().dedupParamMD5(httpServerRequest.getBody(), "requestTime");//计算请求参数摘要，其中剔除里面请求时间的干扰
        String KEY = "dedup:U=" + userId + "M=" + method + "P=" + dedupMD5;

        long expireTime = 1000;// 1000毫秒过期，1000ms内的重复请求会认为重复
        long expireAt = System.currentTimeMillis() + expireTime;
        String val = "expireAt@" + expireAt;

        // NOTE:直接SETNX不支持带过期时间，所以设置+过期不是原子操作，极端情况下可能设置了就不过期了，后面相同请求可能会误以为需要去重，所以这里使用底层API，保证SETNX+过期时间是原子操作
        Boolean firstSet = stringRedisTemplate.execute((RedisCallback<Boolean>) connection -> connection.set(KEY.getBytes(), val.getBytes(), Expiration.milliseconds(expireTime), RedisStringCommands.SetOption.SET_IF_ABSENT));

        final boolean isConsiderDup;
        if (firstSet != null && firstSet) {
            isConsiderDup = false;
        } else {
            isConsiderDup = true;
        }
        return isConsiderDup;
    }
}
