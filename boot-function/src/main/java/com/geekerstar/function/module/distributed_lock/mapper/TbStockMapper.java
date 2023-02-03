package com.geekerstar.function.module.distributed_lock.mapper;

import com.geekerstar.function.config.mybatis_plus.ExpandBaseMapper;
import com.geekerstar.function.module.distributed_lock.entity.TbStock;
import org.springframework.stereotype.Repository;

/**
 * @author geekerstar
 * @date 2023/2/2 17:45
 */
@Repository
public interface TbStockMapper extends ExpandBaseMapper<TbStock> {
}
