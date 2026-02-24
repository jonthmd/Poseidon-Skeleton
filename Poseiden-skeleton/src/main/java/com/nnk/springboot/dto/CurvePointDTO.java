package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurvePointDTO {

    private Integer id;

    @NotNull(message = "Must not be null.")
    private Integer curveId;

    private Double term;
    private Double value;
}
