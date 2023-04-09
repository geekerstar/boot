package com.geekerstar.function.jackson;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2023/4/9 13:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private Integer age;
    private Map<String, Object> diyMap = new HashMap<>();

    @JsonAnySetter
    public void otherField(String key, String value) {
        this.diyMap.put(key, value);
    }
}
