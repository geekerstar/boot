package com.geekerstar.function.module.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geekerstar
 * @date 2023/4/5 14:57
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {
    private String make;
    private int numberOfSeats;
}
