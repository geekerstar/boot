package com.geekerstar.function.module.design_pattern.chain;

import java.util.function.Consumer;

/**
 * @author geekerstar
 * @date 2023/4/22 12:57
 */
@FunctionalInterface
public interface NewProcessor {
    Consumer<String> process(String param);
}
