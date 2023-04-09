package com.geekerstar.function.module.design_pattern.ifelse.case2.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * @date 2023/4/9 12:19
 */
@Data
public class Cart {
    //商品清单
    private List<Item> items = new ArrayList<>();
    //总优惠
    private BigDecimal totalDiscount;
    //商品总价
    private BigDecimal totalItemPrice;
    //总运费
    private BigDecimal totalDeliveryPrice;
    //应付总价
    private BigDecimal payPrice;
}
