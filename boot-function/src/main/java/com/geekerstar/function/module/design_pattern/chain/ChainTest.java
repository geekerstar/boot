package com.geekerstar.function.module.design_pattern.chain;

import java.util.function.Consumer;

/**
 * @author geekerstar
 * @date 2023/4/22 12:57
 * https://mp.weixin.qq.com/s/fRjMSZhRvLV8paziI_5YGA
 */
public class ChainTest {
    public static void main(String[] args) {
        Processor p1 = new ProcessorImpl1(null);
        Processor p2 = new ProcessorImpl2(p1);
        p2.process("something happened");

        Consumer<String> p3 = param -> System.out.println("processor 1 is processing:" + param);
        Consumer<String> p4 = param -> System.out.println("processor 2 is processing:" + param);
        p4.andThen(p3).accept("something happened");
    }
}
