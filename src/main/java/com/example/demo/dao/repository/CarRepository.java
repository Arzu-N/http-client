package com.example.demo.dao.repository;

import com.example.demo.dao.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> ,JpaSpecificationExecutor<Car> {
 //   Page<Car> findByModel(String model, Pageable pageable);

    Page<Car> findByMarka(String marka, Pageable pageable);

    Page<Car>findByPrice(BigDecimal price,Pageable pageable);

    Page<Car>findByAge(Integer age,Pageable pageable);

    @Query("select c from Car c")
    Page<Car>getCars(Pageable pageable);

    @Query("select c from Car c where c.marka=:marka")
    Page<Car>getCars1(@Param("marka")String marka,Pageable pageable);

Optional<Car>findByModel(String model);
}
