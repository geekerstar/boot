package com.geekerstar.function.module.drools.enums;

/**
 * @author geekerstar
 * @date 2023/4/26 20:55
 */
public enum CustomerType {
    LOYAL, NEW, DISSATISFIED;

    public String getValue() {
        return this.toString();
    }
}
