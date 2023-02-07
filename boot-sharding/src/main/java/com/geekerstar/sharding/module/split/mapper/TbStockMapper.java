package com.geekerstar.sharding.module.split.mapper;

import com.geekerstar.sharding.config.mybatis_plus.ExpandBaseMapper;
import com.geekerstar.sharding.module.split.entity.TbStock;
import org.springframework.stereotype.Repository;

/**
 * @author geekerstar
 * @date 2023/2/2 17:45
 */
@Repository
public interface TbStockMapper extends ExpandBaseMapper<TbStock> {
}
