package com.geekerstar.function.jackson;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2023/4/9 13:57
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student2 {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer age;

    @JsonAnyGetter
    private Map<String, Object> initMap = new HashMap() {{
        put("a", 111);
        put("b", 222);
        put("c", 333);
    }};
}
