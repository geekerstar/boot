package com.geekerstar.function.jackson;

import lombok.Data;

import java.util.List;

/**
 * @author geekerstar
 * @date 2023/4/9 13:37
 */
@Data
public class Person {
    private String name;
    private Integer age;
    private List<String> skillList;
}
