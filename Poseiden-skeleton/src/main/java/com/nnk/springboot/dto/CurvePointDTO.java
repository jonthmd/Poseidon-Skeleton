package com.nnk.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurvePointDTO {

    private Integer id;

    @NotNull(message = "Must not be null.")
    @Min(value = 1, message = "Order must be greater than or equal to 1.")
    @Pattern(regexp = "\\d+", message = "Numbers only.")
    private Integer curveId;

    @Min(value = 1, message = "Order must be greater than or equal to 1.")
    private Double term;

    @Min(value = 1, message = "Order must be greater than or equal to 1.")
    private Double value;
}
