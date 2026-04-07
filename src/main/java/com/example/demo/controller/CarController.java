package com.example.demo.controller;

import com.example.demo.CarSpecification;
import com.example.demo.dao.entity.Car;
import com.example.demo.dao.repository.CarRepository;
import com.example.demo.dto.CarDto;
import com.example.demo.dto.CarDto1;
import com.example.demo.dto.CarResponseDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.exception.CarNotFoundException;
import com.example.demo.filter.CarFilter;
import com.example.demo.mapper.CarMapper;
import com.example.demo.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/car")
@Slf4j
public class CarController {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        return ResponseEntity.ok(carRepository.findAll());
    }
    @GetMapping("/pagination")
    public ResponseEntity<Page<Car>> getCarsPagination(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String field,
            @RequestParam String order){
        Sort sort = Sort.unsorted();
        if(order.equals("asc"))
            sort=Sort.by(field);
        else if(order.equals("desc"))
            sort=Sort.by(field).descending();
        return ResponseEntity.ok(carRepository.findAll(PageRequest.of(pageNumber,pageSize,sort)));
    }

    @GetMapping("/pagination1")
    public ResponseEntity<List<Car>>getCarsPagination1(@RequestParam int pageNumber,
                                                       @RequestParam int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize,
                Sort.by(Sort.Direction.ASC, "fiyat")
                        .and(Sort.by(Sort.Direction.DESC, "yash")));
        Page<Car> page = carRepository.findAll(pageable);
        List<Car> content = page.getContent();
        return ResponseEntity.ok(content);
    }
    @GetMapping("/pagination2")
    public ResponseEntity<Page<CarResponseDto>>getCarsPagination2(@RequestParam int pageNumber,
                                                                  @RequestParam int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<Car> page = carRepository.findAll(pageable);
        List<Car> content = page.getContent();
       List<CarResponseDto>responseDto = carMapper.toResponseDtoList(content);
        PageImpl<CarResponseDto> carResponseDtos =
                new PageImpl<>(responseDto, pageable, page.getTotalElements());
        return ResponseEntity.ok(carResponseDtos);
    }

    @GetMapping("/pagination3")
    public ResponseEntity<Page<CarResponseDto>>getCarsPagination3(@RequestParam int pageNumber,
                                                                  @RequestParam int pageSize){
        Sort.Order sortedId = Sort.Order.desc("id");
        Sort.Order sortedFiyat = Sort.Order.asc("fiyat");
        Sort sort = Sort.by(sortedId, sortedFiyat);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Car> page = carRepository.findAll(pageable);
        List<Car> content = page.getContent();
        List<CarResponseDto> responseDtoList = carMapper.toResponseDtoList(content);
        PageImpl<CarResponseDto> carResponseDtos = new PageImpl<>(responseDtoList, pageable, page.getTotalElements());
        return ResponseEntity.ok(carResponseDtos);
    }
    @GetMapping("/pagination4")
    public ResponseEntity<Map<String,Object>>getCarsPagination4(@RequestParam int pageNumber,
                                                                @RequestParam int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Car> page = carRepository.findAll(pageable);
        Map<String, Object> map = Map.of(
                  "Total page: ", page.getTotalPages()
                , "Total elements: ", page.getTotalElements()
                , "Current pageNumber: ", page.getNumber()
                , "Total element in current page: ", page.getNumberOfElements()
                , "Last page: ", page.isLast()
                , "First page: ", page.isFirst()
                , "Content: ", page.getContent()
        );
        return ResponseEntity.ok(map);
    }
/*    @GetMapping("/pagination5")
    public ResponseEntity<Page<CarResponseDto>>getCarsPagination5(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String model){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Car> page = carRepository.findByModel(model, pageable);
        List<Car> content = page.getContent();
        List<CarResponseDto> responseDtoList = carMapper.toResponseDtoList(content);
        PageImpl<CarResponseDto> carResponseDtos = new PageImpl<>(responseDtoList, pageable, page.getTotalElements());
        return  ResponseEntity.ok(carResponseDtos);
    }*/

/*    @GetMapping("/pagination6")
    public ResponseEntity<Page<Car>>getCarsPagination6(@RequestParam int pageNumber,
                                                            @RequestParam int pageSize,
                                                            @RequestParam String field,
                                                            @RequestParam String value){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Car> page;
        switch (field){
            case "marka" :
                page=carRepository.findByMarka(value,pageable);
                break;
            case "model" :
                page=carRepository.findByModel(value,pageable);
                break;
            case "price" :
                page=carRepository.findByPrice(new BigDecimal(value),pageable);
                break;
            case "age" :
                page=carRepository.findByAge(Integer.valueOf(value),pageable);
                break;
            default :
                throw new IllegalArgumentException("Invalid field :"+field);
        }
        return ResponseEntity.ok(page);
    }*/

    @GetMapping("/specification")
    public ResponseEntity<List<Car>>spec(@RequestParam(defaultValue = "",required= false) String marka,
                                         @RequestParam(defaultValue = "",required = false) String model,
                                         @RequestParam(defaultValue = "-1",required = false) BigDecimal price,
                                         @RequestParam(defaultValue = "-1",required= false) int age){

        Specification<Car>spec=(root,query,criteriaBuilder)->
                criteriaBuilder.conjunction();
        if(!marka.isEmpty())
        spec=spec.and(CarSpecification.hasMarka(marka));
        if(!model.isEmpty())
        spec=spec.and(CarSpecification.hasModel(model));
        if(price.compareTo(BigDecimal.valueOf(-1))!=0)
        spec=spec.and(CarSpecification.hasPrice(price));
        if(age!=-1)
        spec=spec.and(CarSpecification.hasAge(age));
        return ResponseEntity.ok(carRepository.findAll(spec));
    }

    @GetMapping("/specification1")
    public ResponseEntity<List<Car>>greaterThanAge(@RequestParam(defaultValue = "-1",required = false)
                                                       Integer age){
        Specification<Car> spec=(root,query,criteriaBuilder)->
                criteriaBuilder.conjunction();
        if(age!=-1)
            spec=spec.and(CarSpecification.greaterThanAge(age));
        return ResponseEntity.ok(carRepository.findAll(spec)) ;
    }

    @GetMapping("/specification2")
    public ResponseEntity<List<Car>>greaterThanPrice(@RequestParam(defaultValue = "-1")BigDecimal price){
        Specification<Car>spec=(root,query,criteriaBuilder)->criteriaBuilder.conjunction();
        if(price.compareTo(BigDecimal.valueOf(-1))!=0)
            spec=spec.and(CarSpecification.greaterThanPrice(price));
        return ResponseEntity.ok(carRepository.findAll(spec));
    }

    @GetMapping("/specification3")
    public ResponseEntity<List<Car>>betweenAge(@RequestParam(defaultValue = "-1",required = false) int minAge,
                                         @RequestParam(defaultValue = "-1",required = false) int maxAge){
        Specification<Car>spec=(root,query,criteriaBuilder)->
                criteriaBuilder.conjunction();
        if(minAge!=-1&&maxAge!=-1)
            spec=spec.and(CarSpecification.betweenAge(minAge,maxAge));
        return ResponseEntity.ok(carRepository.findAll(spec));
    }

    @PostMapping("/specification4")
    public ResponseEntity<List<Car>>addCars(@Valid @RequestBody CarFilter dto){
        Specification<Car> spec = CarSpecification.carFilter(dto);
        List<Car> all = carRepository.findAll(spec);
        return  ResponseEntity.ok(all);
    }

    @GetMapping("/specification5")
    public ResponseEntity<Page<CarResponseDto>>getCars(@RequestParam int pageNumber,
                                                       @RequestParam int pageSize,
                                                       @RequestParam(defaultValue = "-1",required = false) BigDecimal minPrice,
                                                       @RequestParam(defaultValue = "-1",required = false) BigDecimal maxPrice){
       Specification<Car>spec= (root,query,criteriaBuilder)->
               criteriaBuilder.conjunction();
        Specification<Car> carSpecification = CarSpecification.betweenPrice(minPrice, maxPrice);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Car> page = carRepository.findAll(carSpecification, pageable);
        List<CarResponseDto> responseDtoList = carMapper.toResponseDtoList(page.getContent());
        PageImpl<CarResponseDto> carResponseDtos = new PageImpl<>(responseDtoList, pageable, page.getTotalElements());
        return ResponseEntity.ok(carResponseDtos);
    }

    @GetMapping("/spec-page-sort")
    public ResponseEntity<Page<Car>>getCars(@RequestParam(defaultValue = "0",required = false)int pageNumber,
                                            @RequestParam(defaultValue = "10",required = false)int pageSize,
                                            @RequestParam(required = false) String marka,
                                            @RequestParam(required = false)String model,
                                            @RequestParam(defaultValue = "id")String sortedField,
                                            @RequestParam(defaultValue = "asc")String sortedDirection){
        Sort sort=(sortedDirection.equalsIgnoreCase("desc"))?Sort.by(sortedField).descending():Sort.by(sortedField).ascending();
        Pageable pageable  = PageRequest.of(pageNumber, pageSize, sort);
        Specification<Car>spec=(root,query,criteriaBuilder)->criteriaBuilder.conjunction();
        if(marka!=null&&!marka.isEmpty())
             spec = spec.and(CarSpecification.hasMarka(marka));
        if(model!=null&&!model.isEmpty())
            spec=spec.and(CarSpecification.hasModel(model));
        Page<Car> page = carRepository.findAll(spec, pageable);
        return ResponseEntity.ok(page);

    }
/*    @PostMapping
    public ResponseEntity<Void>add(@RequestBody CarDto dto){
        Car car = new Car();
        car.setMarka(dto.getMarka());
        car.setModel(dto.getModel());
        car.setAge(dto.getYash());
        car.setPrice(dto.getFiyat());
        carRepository.save(car);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }*/

    @GetMapping("/jpql")
    public ResponseEntity<Page<Car>>getCarsJpql(@RequestParam(defaultValue = "0")int pageNumber,
                                          @RequestParam(defaultValue = "10")int pageSize){
       Pageable pageable = PageRequest.of(pageNumber, pageSize);
       return  ResponseEntity.ok(carRepository.getCars(pageable));
    }

    @GetMapping("/jpql1")
    public ResponseEntity<Page<Car>>getCarsJpql1(@RequestParam(defaultValue = "0",required=false)int pageNumber,
                                                 @RequestParam(defaultValue = "10",required = false)int pageSize,
                                                 @RequestParam String marka){
        Pageable pageable=PageRequest.of(pageNumber,pageSize,Sort.by("id").descending());
        Page<Car> cars1 = carRepository.getCars1(marka, pageable);
        return ResponseEntity.ok(cars1);
    }

    @PostMapping("/post")
    public ResponseEntity<CarResponseDto>addCar(@Valid @RequestBody CarDto dto){
      return ResponseEntity.status(HttpStatus.CREATED).body(carService.addCar(dto));
    }

    @GetMapping("/get-{id}")
    public ResponseEntity<CarResponseDto>getCar(@PathVariable Long id){
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<CarResponseDto>>getCarss(@RequestHeader HttpHeaders headers){
headers.forEach((key,value)-> System.out.println("header: "+key+"=>"+value));
        return ResponseEntity.ok(carService.getCars());
    }

    @PutMapping("/put-{id}")
    public ResponseEntity<CarResponseDto>updateCar(@PathVariable Long id,
                                                   @RequestBody CarDto dto){
        return ResponseEntity.ok(carService.updateCar(id,dto));
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Void>deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CarResponseDto>getCarById(@PathVariable @Min(1) Long id){
   return ResponseEntity.ok(carService.getCarById(id));
    }

    @GetMapping("/get-by-id1")
    public ResponseEntity<CarResponseDto>getCarById1(@RequestParam @Min(1) Long id){
        return ResponseEntity.ok(carService.getCarById(id));
    }


    @GetMapping("/by-model")
    public ResponseEntity<CarResponseDto>getCarByModel(@RequestParam String model){
        Car car = carRepository.findByModel(model).orElseThrow(() ->
                new CarNotFoundException("Car not found with "+model+" model"));
        return ResponseEntity.ok(carMapper.toResponseDto(car));
    }


    @PostMapping("/post1")
    public ResponseEntity<CarResponseDto>addCar(@Valid @RequestBody CarDto1 dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.addCar1(dto));
    }

@PostMapping("/post/{model}")
    public ResponseEntity<CarResponseDto>addCar(@PathVariable String model,
                                                @RequestParam BigDecimal price,
                                                @Valid @RequestBody CarDto1 dto){
    Car car = new Car();
    car.setModel(model);
    car.setPrice(price);
    car.setMarka(dto.getMarka());
    car.setAge(dto.getAge());
    return ResponseEntity.status(HttpStatus.CREATED).body(carMapper.toResponseDto(carRepository.save(car)));
}

@PutMapping("/update1/{id}")
    public ResponseEntity<?>updateCar(@PathVariable @Positive(message="id must be positive") Long id, @Valid @RequestBody CarDto1 dto){
        return ResponseEntity.ok(carService.updateCar1(id,dto));
}


@GetMapping("/header")
    public ResponseEntity<?>getHeader(@RequestHeader("header-bir")String header){
        log.info(header);
        return ResponseEntity.ok(header);
}



@GetMapping("/header1")
public ResponseEntity<?> getHeader1(@RequestHeader("Authorization")String token) {
        log.info(token);
        return ResponseEntity.ok(token);
    
}


@GetMapping("/header2")
    public ResponseEntity<?>getHeader2(@RequestHeader("header-bir")String header1){
        log.info(header1);
        return ResponseEntity.ok().header("sonuc",header1).body(header1);
}

@GetMapping("/header3")
    public ResponseEntity<?>getHeader3(@RequestHeader("Authorization")String token){
        log.info(token);
        return ResponseEntity.status(200).header("Authorization",token).body(token);
}

@GetMapping("/header4")
    public ResponseEntity<?>getHeader4(){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Authorization","token");
    httpHeaders.add("deneme-header","deneme");
    return ResponseEntity.status(200).headers(httpHeaders).body("header,token");
}



}
