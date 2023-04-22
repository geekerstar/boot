package com.geekerstar.function.module.design_pattern.template;

/**
 * @author geekerstar
 * @date 2023/4/22 12:48
 */
public abstract class AbstractPushTemplate {

    public void push(int customerId, String shopName) {
        System.out.println("准备推送...");
        execute(customerId, shopName);
        System.out.println("推送完成\n");
    }

    abstract protected void execute(int customerId, String shopName);
}
