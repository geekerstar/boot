package com.geekerstar.function.module.transaction.service.invalid;

import com.geekerstar.function.module.distributed_lock.entity.TbStock;
import com.geekerstar.function.module.distributed_lock.mapper.TbStockMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author geekerstar
 * @date 2023/2/5 10:52
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InvalidOtherClassWithTransaction {

    private final TbStockMapper tbStockMapper;

    @Transactional
    public void methodTransaction(){
        TbStock tbStock = new TbStock();
        tbStock.setId(434L);
        tbStock.setProductCode("232");
        tbStock.setWarehouse("213");
        tbStock.setCount(232);
        tbStock.setVersion(123);
        tbStockMapper.insert(tbStock);
        throw new RuntimeException("测试回滚");
    }
}
