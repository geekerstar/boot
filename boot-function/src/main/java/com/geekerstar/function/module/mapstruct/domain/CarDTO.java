package com.geekerstar.function.module.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geekerstar
 * @date 2023/4/5 14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private String make;
    private int seatCount;
    private String type;

}
