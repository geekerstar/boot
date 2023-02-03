package com.geekerstar.function.module.distributed_lock.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.geekerstar.function.exception.BusinessException;
import com.geekerstar.function.module.distributed_lock.entity.TbStock;
import com.geekerstar.function.module.distributed_lock.mapper.TbStockMapper;
import com.geekerstar.function.module.distributed_lock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author geekerstar
 * @date 2023/2/2 17:42
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final TbStockMapper tbStockMapper;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;


    @Override
    public void deductOptimisticLock() {
        int update = tbStockMapper.update(null,
                new LambdaUpdateWrapper<TbStock>()
                        .setSql("count = count - " + 1)
                        .eq(TbStock::getId, 1L)
                        .apply("count >= {0}", 1)
        );
        if (update == 0) {
            log.error("库存扣减失败");
            throw new BusinessException("", "库存扣减失败");
        }
    }

    @Override
    public void deductByRedission() {
        RLock lock = redissonClient.getLock("lock1");
        lock.lock();
        try {
            // 查询库存数
            TbStock tbStock = tbStockMapper.selectById(1);
            if (null != tbStock && tbStock.getCount() > 0) {
                // 库存充足
                int update = tbStockMapper.update(null,
                        new LambdaUpdateWrapper<TbStock>()
                                .setSql("count = count - " + 1)
                                .eq(TbStock::getId, 1L)
                );
                if (update == 0) {
                    log.error("库存扣减失败");
                    throw new BusinessException("", "库存扣减失败");
                }
            } else {
                throw new BusinessException("", "库存不足");
            }
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }

    }

    @Override
    public void testSemaphore() {
        // 将semaphore作为key并缓存到redis中
        RSemaphore semaphore = redissonClient.getSemaphore("semaphore");
        // 设置资源量 限流的线程数
        semaphore.trySetPermits(3);

        try {
            semaphore.acquire();
            // 将打印信息统一存储在redis中
            this.stringRedisTemplate.opsForList().rightPush("log", "10010获取了资源，开始处理业务逻辑。。。" + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(10 + new Random().nextInt(10));
            this.stringRedisTemplate.opsForList().rightPush("log", "10010释放了资源，释放资源。。。" + Thread.currentThread().getName());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
