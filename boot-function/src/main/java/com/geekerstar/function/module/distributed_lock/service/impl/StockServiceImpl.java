package com.geekerstar.function.module.distributed_lock.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.geekerstar.function.module.distributed_lock.entity.TbStock;
import com.geekerstar.function.module.distributed_lock.mapper.TbStockMapper;
import com.geekerstar.function.module.distributed_lock.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/2/2 17:42
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final TbStockMapper tbStockMapper;

    @Override
    public void deduct() {
        int update = tbStockMapper.update(null,
                new LambdaUpdateWrapper<TbStock>()
                        .setSql("count = count - " + 1)
                        .eq(TbStock::getId, 1L)
                        .apply("count >= {0}", 1)
        );
        if (update == 0) {
            log.error("库存扣减失败");
        }
    }
}
