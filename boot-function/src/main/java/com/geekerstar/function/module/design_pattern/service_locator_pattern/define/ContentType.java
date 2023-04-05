package com.geekerstar.function.module.design_pattern.service_locator_pattern.define;

/**
 * @author geekerstar
 * @date 2023/4/5 18:15
 */
public enum ContentType {
    JSON(TypeConstants.JSON_PARSER),
    CSV(TypeConstants.CSV_PARSER),
    XML(TypeConstants.XML_PARSER);
    private final String parserName;

    ContentType(String parserName) {
        this.parserName = parserName;
    }

    @Override
    public String toString() {
        return this.parserName;
    }

}
