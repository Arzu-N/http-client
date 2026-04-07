package org.example.resttemplate.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.example.resttemplate.feign.FeignClient1;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeignService {

    private final FeignClient1 feignClient1;

    public List<CarResponseDto>getCars(){
        try{
        return  feignClient1.getCars().getBody();}
        catch(FeignException e){
        throw new RuntimeException(e.getMessage());
        }
    }

    public CarResponseDto getCarById(Long id){
        try{
        return feignClient1.getCarById(id).getBody();}
        catch(FeignException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public CarResponseDto getCarByModel(String model){
        try{
        return feignClient1.getCarByModel(model).getBody();}
        catch(FeignException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public CarResponseDto addCar(CarDto1 dto){
        try{
       return feignClient1.addCar(dto).getBody();}
        catch(FeignException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public CarResponseDto updateCar(Long id,CarDto1 dto){
        try{
        return feignClient1.updateCar(id,dto).getBody();}
        catch(FeignException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteCar(Long id){
        try{
        feignClient1.deleteCar(id);}
        catch(FeignException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
