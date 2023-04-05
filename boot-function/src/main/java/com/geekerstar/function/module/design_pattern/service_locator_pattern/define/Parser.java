package com.geekerstar.function.module.design_pattern.service_locator_pattern.define;

import java.io.Reader;
import java.util.List;

/**
 * @author geekerstar
 * @date 2023/4/5 18:15
 */
public interface Parser {
    List parse(Reader r);
}
