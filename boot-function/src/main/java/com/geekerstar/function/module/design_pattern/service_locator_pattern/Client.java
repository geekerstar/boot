package com.geekerstar.function.module.design_pattern.service_locator_pattern;

import com.geekerstar.function.module.design_pattern.service_locator_pattern.define.ContentType;
import com.geekerstar.function.module.design_pattern.service_locator_pattern.define.ParserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Client {
    private final ParserFactory parserFactory;

    public List getAll(ContentType contentType) {

        FileReader fileReader;
        try {
            fileReader = new FileReader("");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 关键点，直接根据类型获取
        return parserFactory
                .getParser(contentType)
                .parse(fileReader);
    }
}
