package com.geekerstar.function.module.design_pattern.ifelse.case2.service;

import com.geekerstar.function.module.design_pattern.ifelse.case2.entity.Item;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author geekerstar
 * @date 2023/4/9 12:30
 */
@Service(value = "InternalUserCart")
public class InternalUserCart extends AbstractCart {

    @Override
    protected void processCouponPrice(long userId, Item item) {
        item.setCouponPrice(BigDecimal.ZERO);
    }

    @Override
    protected void processDeliveryPrice(long userId, Item item) {
        item.setDeliveryPrice(BigDecimal.ZERO);
    }
}
