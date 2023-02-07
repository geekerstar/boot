package com.geekerstar.sharding.module.split.service;

import com.geekerstar.sharding.module.split.entity.TbStock;
import com.geekerstar.sharding.module.split.mapper.TbStockMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author geekerstar
 * @date 2023/2/7 19:07
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SplitService {

    private final TbStockMapper tbStockMapper;

    public TbStock select(Long id) {
        return tbStockMapper.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(TbStock tbStock) {
        tbStockMapper.insert(tbStock);
    }
}
