package com.geekerstar.function.module.transaction.service.invalid;

import com.geekerstar.function.module.distributed_lock.entity.TbStock;
import com.geekerstar.function.module.distributed_lock.mapper.TbStockMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author geekerstar
 * @date 2023/2/5 10:44
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InvalidSameClass {

    private final TbStockMapper tbStockMapper;

    public void methodNoTransaction(){
        this.methodTransaction();
    }

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

    /**
     * private 注解失效
     */
    @Transactional
    private void method() {
        // 写数据库操作
    }

    /**
     * 跨数据源事务失效，如果要在多个数据源之间实现事务，那么可以引入JTA
     */
    @Transactional
    public void differentData() {
        // 数据源1
        // 数据源2
    }

    /**
     * 默认情况下，仅对RuntimeException和Error进行回滚。如果不是的它们及它们的子孙异常的话，就不会回滚
     * 如果要影响事务回滚，可以定义为RuntimeException的子类；如果不是RuntimeException，但也希望触发回滚，那么可以使用rollbackFor属性来指定要回滚的异常。
     *
     */
//    @Transactional(rollbackFor = XXXException.class)
//    public void rollbackException() throws XXXException {
//
//    }
}
