package com.geekerstar.function.module.event.event;

import lombok.Data;

import java.io.Serializable;

/**
 * @author geekerstar
 * @date 2023/4/5 14:14
 */
@Data
public class PlaceOrderEventMessage implements Serializable {
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 订单状态
     */
    private Integer orderStatus;
    /**
     * 下单用户ID
     */
    private String userId;
    //……
}
