package com.geekerstar.function.module.design_pattern.template;

import java.util.function.Consumer;

/**
 * @author geekerstar
 * @date 2023/4/22 12:50
 */
public class PushTemplateLambda {

    public void push(int customerId, String shopName, Consumer<Object[]> execute) {
        System.out.println("准备推送...");
        Object[] param = new Object[]{customerId, shopName};
        execute.accept(param);
        System.out.println("推送完成\n");
    }
}
