package com.geekerstar.function.module.design_pattern.ifelse.case2.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author geekerstar
 * @date 2023/4/9 12:19
 */
@Data
public class Item {
    //商品ID
    private long id;
    //商品数量
    private int quantity;
    //商品单价
    private BigDecimal price;
    //商品优惠
    private BigDecimal couponPrice;
    //商品运费
    private BigDecimal deliveryPrice;
}
