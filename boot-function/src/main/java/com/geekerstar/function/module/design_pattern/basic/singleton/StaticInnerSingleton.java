package com.geekerstar.function.module.design_pattern.basic.singleton;

/**
 * @author geekerstar
 * @date 2023/2/19 16:40
 */
public class StaticInnerSingleton {

    private StaticInnerSingleton(){}

    private static class SingletonHolder{
        private static final StaticInnerSingleton STATIC_INNER_SINGLETON =new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance(){
        return SingletonHolder.STATIC_INNER_SINGLETON;
    }
}
