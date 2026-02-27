package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleNameDTO {

    private Integer id;

    @NotBlank(message = "Name is mandatory.")
    private String name;

    private String description;
    private String json;
    private String template;
    private String sql;
    private String sqlPart;
}
