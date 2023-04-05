package com.geekerstar.function.module.mapstruct.mapper;

import com.geekerstar.function.module.mapstruct.domain.Car;
import com.geekerstar.function.module.mapstruct.domain.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author geekerstar
 * @date 2023/4/5 15:02
 */
@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDTO carToCarDTO(Car car);
    

}
