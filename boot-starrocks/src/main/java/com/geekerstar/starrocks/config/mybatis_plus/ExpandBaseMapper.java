package com.geekerstar.starrocks.config.mybatis_plus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author geekerstar
 * @date 2023/2/2 17:46
 */
public interface ExpandBaseMapper<T> extends BaseMapper<T> {

    /**
     * 全字段更新，不会忽略null值
     *
     * @param entity 实体对象
     */
    int alwaysUpdateSomeColumnById(@Param("et") T entity);

    /**
     * 全量插入,等价于insert
     *
     * @param entityList 实体集合
     */
    int insertBatchSomeColumn(List<T> entityList);
}
