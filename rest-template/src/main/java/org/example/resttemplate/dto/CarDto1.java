package org.example.resttemplate.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto1 {
 //   @Size(message = "Character limit exceeded", min = 5, max = 15)
  //  @NotBlank(message = "Marka cannot be blank")
    String marka;
//    @NotBlank(message = "Character cannot be blank")
    String model;
 //   @NotNull
   // @PositiveOrZero(message = "Price must be positive or zero")
    BigDecimal price;
   // @Positive
    Integer age;
}