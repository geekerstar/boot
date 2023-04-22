package com.geekerstar.function.module.design_pattern.chain;

/**
 * @author geekerstar
 * @date 2023/4/22 12:56
 */
public class ProcessorImpl1 extends AbstractProcessor {

    public ProcessorImpl1(Processor processor) {
        super(processor);
    }

    @Override
    public void process(String param) {
        System.out.println("processor 1 is processing:" + param);
        if (getNextProcessor() != null) {
            getNextProcessor().process(param);
        }
    }
}
