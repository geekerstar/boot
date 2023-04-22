package com.geekerstar.function.module.design_pattern.chain;

/**
 * @author geekerstar
 * @date 2023/4/22 12:57
 */
public class ProcessorImpl2 extends AbstractProcessor {

    public ProcessorImpl2(Processor next) {
        super(next);
    }

    @Override
    public void process(String param) {
        System.out.println("processor 2 is processing:" + param);
        if (getNextProcessor() != null) {
            getNextProcessor().process(param);
        }
    }
}
