package com.nnk.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidListDTO {

    private Integer id;

    @NotBlank(message = "Account is mandatory.")
    private String account;

    @NotBlank(message = "Type is mandatory.")
    private String type;

    @Min(value = 1, message = "Order must be greater than or equal to 1.")
    private Double bidQuantity;
}
