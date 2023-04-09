package com.geekerstar.function.module.design_pattern.ifelse.case3.entity;

import com.geekerstar.function.module.design_pattern.ifelse.case3.annotation.BankAPI;
import com.geekerstar.function.module.design_pattern.ifelse.case3.annotation.BankAPIField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author geekerstar
 * @date 2023/4/9 12:48
 */
@BankAPI(url = "/bank/pay", desc = "支付接口")
@Data
public class PayAPI extends AbstractAPI {
    @BankAPIField(order = 1, type = "N", length = 20)
    private long userId;
    @BankAPIField(order = 2, type = "M", length = 10)
    private BigDecimal amount;
}
