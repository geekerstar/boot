package com.geekerstar.tdengine.module.tdengine.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.geekerstar.tdengine.config.mybatis_plus.ExpandBaseMapper;
import com.geekerstar.tdengine.module.tdengine.domain.entity.Meters;
import org.springframework.stereotype.Repository;

/**
 * @author geekerstar
 * @date 2023/5/28 09:29
 */
@DS("tdengine")
@Repository
public interface MetersMapper extends ExpandBaseMapper<Meters> {

    Meters selectPower();

}
