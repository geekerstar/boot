package com.geekerstar.function.module.design_pattern.chain;

/**
 * @author geekerstar
 * @date 2023/4/22 12:56
 */
public interface Processor {

    Processor getNextProcessor();

    void process(String param);
}
