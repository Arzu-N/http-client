package com.example.demo.service;

import com.example.demo.dao.entity.Car;
import com.example.demo.dao.repository.CarRepository;
import com.example.demo.dto.CarDto;
import com.example.demo.dto.CarDto1;
import com.example.demo.dto.CarResponseDto;
import com.example.demo.exception.CarAlreadyExistException;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarResponseDto addCar(CarDto dto){
        Optional<Car> byModel = carRepository.findByModel(dto.getModel());
        if(byModel.isPresent())
               throw  new CarAlreadyExistException("Car already exist ");
       return carMapper.toResponseDto(carRepository.save(carMapper.toEntity(dto)));
    }

    public CarResponseDto addCar1(CarDto1 dto){
        Optional<Car> byModel = carRepository.findByModel(dto.getModel());
        if(byModel.isPresent())
            throw  new CarAlreadyExistException("Car already exist ");
        return carMapper.toResponseDto(carRepository.save(carMapper.toEntity(dto)));
    }



    public CarResponseDto getCarById(Long id){
        Car car = carRepository.findById(id).orElseThrow(() ->
                new CarNotFoundException("Car not found"));
        return carMapper.toResponseDto(car);
    }

    public List<CarResponseDto> getCars(){
        return  carMapper.toResponseDtoList(carRepository.findAll());
    }

    public CarResponseDto updateCar(Long id,CarDto dto){
        Car car = carRepository.findById(id).orElseThrow(() ->
                new CarNotFoundException("Car not found"));
        car.setMarka(dto.getMarka());
        car.setModel(dto.getModel());
        car.setAge(dto.getAge());
        car.setPrice(dto.getPrice());
       return carMapper.toResponseDto(carRepository.save(car));
    }

    public void deleteCar(Long id){
        carRepository.findById(id).orElseThrow(()->
                new CarNotFoundException("Car not found"));
        carRepository.deleteById(id);
    }

    public CarResponseDto updateCar1(Long id,CarDto1 dto){
        Car car = carRepository.findById(id).orElseThrow(() ->
                new CarNotFoundException("Car not found"));
        car.setMarka(dto.getMarka());
        car.setModel(dto.getModel());
        car.setAge(dto.getAge());
        car.setPrice(dto.getPrice());
        return carMapper.toResponseDto(carRepository.save(car));
    }


}
