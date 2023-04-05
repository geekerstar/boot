package com.geekerstar.function.module.design_pattern.service_locator_pattern.parser;

import com.geekerstar.function.module.design_pattern.service_locator_pattern.define.Parser;
import com.geekerstar.function.module.design_pattern.service_locator_pattern.define.TypeConstants;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.List;

/**
 * @author geekerstar
 * @date 2023/4/5 18:24
 */
@Component(TypeConstants.JSON_PARSER)
public class JSONParser implements Parser {

    @Override
    public List parse(Reader r) {
        return null;
    }
}
