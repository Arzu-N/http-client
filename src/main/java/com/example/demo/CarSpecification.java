package com.example.demo;

import com.example.demo.dao.entity.Car;
import com.example.demo.filter.CarFilter;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CarSpecification {
    public static Specification<Car>hasMarka(String marka){
        return (root,query,criteriaBuilder)->{
            return criteriaBuilder.equal(root.get("marka"),marka);
        };
    }

    public static Specification<Car>hasModel(String model){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("model"),model);
        };
    }

    public static Specification<Car>hasPrice(BigDecimal price){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("price"),price);
        };
    }

    public static Specification<Car>hasAge(Integer age){
        return (root, query, criteriaBuilder) ->{
          return  criteriaBuilder.equal(root.get("age"),age);
        };
    }

    public static Specification<Car>greaterThanAge(Integer age){
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.greaterThan(root.get("age"),age);
        };
    }

    public static Specification<Car>lessThanAge(Integer age){
        return (root, query, criteriaBuilder) ->{
            return criteriaBuilder.lessThan(root.get("age"),age);
        };
    }

    public static Specification<Car>greaterThanPrice(BigDecimal price){
        return (root,query,criteriaBuilder)->{
            return criteriaBuilder.greaterThan(root.get("price"),price);
        };
    }
    public static Specification<Car>betweenAge(int minAge,int maxAge){
        return (root,query,criteriaBuilder)->{
            return criteriaBuilder.between(root.get("age"),minAge,maxAge);
        };
    }

    public static Specification<Car> carFilter(CarFilter dto){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("marka"),dto.getMarka()),
                        criteriaBuilder.equal(root.get("model"),dto.getModel()),
                        criteriaBuilder.greaterThan(root.get("age"),dto.getMinAge()),
                        criteriaBuilder.lessThan(root.get("age"),dto.getMaxAge()),
                        criteriaBuilder.greaterThan(root.get("price"),dto.getMinPrice()),
                        criteriaBuilder.lessThan(root.get("price"),dto.getMaxPrice())));
    }

    public static Specification<Car>betweenPrice(BigDecimal minPrice,BigDecimal maxPrice){
        return (root,query,criteriaBuilder)->
                criteriaBuilder.between(root.get("price"),minPrice,maxPrice);
    }
}
