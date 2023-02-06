package com.geekerstar.function.limit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * @author geekerstar
 * @date 2023/2/6 15:30
 * <p>
 * Redis 固定窗口限流
 */
@SpringBootTest
public class RedisLuaLimiterByIncrTest {
    private static String KEY_PREFIX = "limiter_";
    private static String QPS = "4";
    private static String EXPIRE_TIME = "1";

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
        // 当前秒数作为 key
        key = KEY_PREFIX + key + System.currentTimeMillis() / 1000;
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        //lua文件存放在resources目录下
        redisScript.setScriptText("local count = redis.call(\"incr\",KEYS[1])\n" +
                "if count == 1 then\n" +
                "  redis.call('expire',KEYS[1],ARGV[2])\n" +
                "end\n" +
                "if count > tonumber(ARGV[1]) then\n" +
                "  return 0\n" +
                "end\n" +
                "return 1");
//        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limiter.lua")));
        return stringRedisTemplate.execute(redisScript, Arrays.asList(key), QPS, EXPIRE_TIME) == 1;
    }
}
