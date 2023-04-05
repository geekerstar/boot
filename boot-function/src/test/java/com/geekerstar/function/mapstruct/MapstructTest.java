package com.geekerstar.function.mapstruct;

import com.geekerstar.function.module.mapstruct.domain.Car;
import com.geekerstar.function.module.mapstruct.domain.CarDTO;
import com.geekerstar.function.module.mapstruct.mapper.CarMapper;
import org.junit.Test;

/**
 * @author geekerstar
 * @date 2023/4/5 15:13
 */
public class MapstructTest {

    @Test
    public void shouldMapCarToDto() {

        Car car = new Car("Morris", 5);
        CarDTO carDto = CarMapper.INSTANCE.carToCarDTO(car);
        System.out.println(carDto);

    }
}
