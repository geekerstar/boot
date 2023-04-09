package com.geekerstar.function.module.design_pattern.ifelse.case2.mapper;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @author geekerstar
 * @date 2023/4/9 12:30
 */
@Repository
public interface DbMapper {
    int getUserCouponPercent(long userId);

    BigDecimal getItemPrice(Long key);

    String getUserCategory(int userId);
}
