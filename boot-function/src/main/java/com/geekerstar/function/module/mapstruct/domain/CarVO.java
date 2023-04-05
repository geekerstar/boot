package com.geekerstar.function.module.mapstruct.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author geekerstar
 * @date 2023/4/5 15:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarVO {

    private String make;
    private int seatCount;
    private boolean type;
}
