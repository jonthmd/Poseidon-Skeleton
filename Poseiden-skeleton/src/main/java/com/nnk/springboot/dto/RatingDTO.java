package com.nnk.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {

    private Integer id;
    @NotBlank(message = "Rating is mandatory.")
    private String moodysRating;
    @NotBlank(message = "Rating is mandatory.")
    private String sandPRating;
    @NotBlank(message = "Rating is mandatory.")
    private String fitchRating;
    @Min(value = 1, message = "Order must be greater than or equal to 1.")
    private Integer order;
}
