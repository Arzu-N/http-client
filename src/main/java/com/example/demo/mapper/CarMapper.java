package com.example.demo.mapper;

import com.example.demo.dao.entity.Car;
import com.example.demo.dto.CarDto;
import com.example.demo.dto.CarDto1;
import com.example.demo.dto.CarResponseDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarResponseDto toResponseDto(Car car);

    List<CarResponseDto> toResponseDtoList(List<Car> list);

    Car toEntity(CarDto dto);

    List<Car> toEntityList(List<CarDto> list);

    Car toEntity(CarDto1 carDto1);

    CarDto1 toDto(Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarDto1 carDto1, @MappingTarget Car car);
}
