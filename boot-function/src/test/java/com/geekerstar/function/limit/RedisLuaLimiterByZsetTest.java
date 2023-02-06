package com.geekerstar.function.limit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * @author geekerstar
 * @date 2023/2/6 15:33
 */
@SpringBootTest
public class RedisLuaLimiterByZsetTest {
    private String KEY_PREFIX = "limiter_";
    private String QPS = "4";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisLuaLimiterTests() throws InterruptedException, IOException {
        for (int i = 0; i < 15; i++) {
            Thread.sleep(200);
            System.out.println(LocalTime.now() + " " + acquire("user1"));
        }
    }

    /**
     * 计数器限流
     *
     * @param key
     * @return
     */
    public boolean acquire(String key) {
        long now = System.currentTimeMillis();
        key = KEY_PREFIX + key;
        String oldest = String.valueOf(now - 1_000);
        String score = String.valueOf(now);
        String scoreValue = score;
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptText("--KEYS[1]: 限流 key\n" +
                "--ARGV[1]: 时间戳 - 时间窗口\n" +
                "--ARGV[2]: 当前时间戳（作为score）\n" +
                "--ARGV[3]: 阈值\n" +
                "--ARGV[4]: score 对应的唯一value\n" +
                "-- 1. 移除时间窗口之前的数据\n" +
                "redis.call('zremrangeByScore', KEYS[1], 0, ARGV[1])\n" +
                "-- 2. 统计当前元素数量\n" +
                "local res = redis.call('zcard', KEYS[1])\n" +
                "-- 3. 是否超过阈值\n" +
                "if (res == nil) or (res < tonumber(ARGV[3])) then\n" +
                "    redis.call('zadd', KEYS[1], ARGV[2], ARGV[4])\n" +
                "    return 1\n" +
                "else\n" +
                "    return 0\n" +
                "end");
        //lua文件存放在resources目录下
//        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limiter2.lua")));
        return stringRedisTemplate.execute(redisScript, Arrays.asList(key), oldest, score, QPS, scoreValue) == 1;
    }
}
