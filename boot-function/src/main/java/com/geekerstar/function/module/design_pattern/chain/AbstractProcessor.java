package com.geekerstar.function.module.design_pattern.chain;

/**
 * @author geekerstar
 * @date 2023/4/22 12:56
 */
public abstract class AbstractProcessor implements Processor {

    private Processor next;

    public AbstractProcessor(Processor processor) {
        this.next = processor;
    }

    @Override
    public Processor getNextProcessor() {
        return next;
    }

    @Override
    public abstract void process(String param);
}
