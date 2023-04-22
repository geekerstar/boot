package com.geekerstar.function.module.design_pattern.observer;

/**
 * @author geekerstar
 * @date 2023/4/22 12:52
 */
public interface Subject {
    void registerObserver(Observer o);
    void notifyAllObserver(String orderNo);
}

