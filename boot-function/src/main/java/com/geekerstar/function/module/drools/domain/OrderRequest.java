package com.geekerstar.function.module.drools.domain;

import com.geekerstar.function.module.drools.enums.CustomerType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author geekerstar
 * @date 2023/4/26 20:54
 */
@Getter
@Setter
public class OrderRequest {
    /**
     * 客户号
     */
    private String customerNumber;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 订单金额
     */
    private Integer amount;
    /**
     * 客户类型
     */
    private CustomerType customerType;
}
