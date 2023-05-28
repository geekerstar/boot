package com.geekerstar.tdengine.module.tdengine.service.impl;

import com.geekerstar.tdengine.module.tdengine.domain.entity.Meters;
import com.geekerstar.tdengine.module.tdengine.mapper.MetersMapper;
import com.geekerstar.tdengine.module.tdengine.service.TdengineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/5/28 09:26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TdengineServiceImpl implements TdengineService {

    private final MetersMapper metersMapper;

    @Override
    public Meters selectPower() {
        return metersMapper.selectPower();
    }
}
