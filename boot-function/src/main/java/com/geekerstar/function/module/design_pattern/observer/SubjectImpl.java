package com.geekerstar.function.module.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * @date 2023/4/22 12:52
 */
public class SubjectImpl implements Subject {
    private final List<Observer> list = new ArrayList<>();
    @Override
    public void registerObserver(Observer o) {
        list.add(o);
    }
    @Override
    public void notifyAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));
    }
}
