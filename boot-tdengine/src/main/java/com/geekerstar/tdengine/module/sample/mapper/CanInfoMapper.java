package com.geekerstar.tdengine.module.sample.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.geekerstar.tdengine.config.mybatis_plus.ExpandBaseMapper;
import com.geekerstar.tdengine.module.sample.domain.entity.CanInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author geekerstar
 * @date 2023/5/12 10:53
 */
@DS("tdengine")
@Repository
public interface CanInfoMapper extends ExpandBaseMapper<CanInfo> {
    void createAndInsert(@Param("carNo") Integer 车辆编号, @Param("can") CanInfo canInfo);
}
