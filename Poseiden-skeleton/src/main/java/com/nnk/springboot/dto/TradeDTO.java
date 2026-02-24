package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO {

    private Integer id;

    @NotBlank(message = "Account is mandatory.")
    private String account;

    @NotBlank(message = "Type is mandatory.")
    private String type;

    private Double buyQuantity;
}
