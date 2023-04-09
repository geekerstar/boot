package com.geekerstar.function.jackson;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * @author geekerstar
 * @date 2023/4/9 13:52
 */
@Data
public class Cat {

    @JsonSetter(value = "catName")
    private String name;

//    @JsonIgnore
    private Integer age;

    @JsonGetter(value = "catName")
    public String getName() {
        return name;
    }
}
