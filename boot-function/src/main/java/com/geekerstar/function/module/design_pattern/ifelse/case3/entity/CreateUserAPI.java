package com.geekerstar.function.module.design_pattern.ifelse.case3.entity;

import com.geekerstar.function.module.design_pattern.ifelse.case3.annotation.BankAPI;
import com.geekerstar.function.module.design_pattern.ifelse.case3.annotation.BankAPIField;
import lombok.Data;

/**
 * @author geekerstar
 * @date 2023/4/9 12:46
 */
@BankAPI(url = "/bank/createUser", desc = "创建用户接口")
@Data
public class CreateUserAPI extends AbstractAPI {
    @BankAPIField(order = 1, type = "S", length = 10)
    private String name;
    @BankAPIField(order = 2, type = "S", length = 18)
    private String identity;
    @BankAPIField(order = 4, type = "S", length = 11) //注意这里的order需要按照API表格中的顺序
    private String mobile;
    @BankAPIField(order = 3, type = "N", length = 5)
    private int age;
}
