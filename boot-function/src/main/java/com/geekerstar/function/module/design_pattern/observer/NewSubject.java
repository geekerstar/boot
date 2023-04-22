package com.geekerstar.function.module.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * @date 2023/4/22 12:54
 */
public interface NewSubject {

    List<Observer> list = new ArrayList<>();

    default void registerObserver(Observer o) {
        list.add(o);
    }

    default void nofityAllObserver(String orderNo) {
        list.forEach(c -> c.notify(orderNo));
    }
}
