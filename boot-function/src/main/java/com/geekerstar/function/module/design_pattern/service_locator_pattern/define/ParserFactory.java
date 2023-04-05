package com.geekerstar.function.module.design_pattern.service_locator_pattern.define;

/**
 * @author geekerstar
 * @date 2023/4/5 18:14
 */

public interface ParserFactory {
    Parser getParser(ContentType contentType);
}
