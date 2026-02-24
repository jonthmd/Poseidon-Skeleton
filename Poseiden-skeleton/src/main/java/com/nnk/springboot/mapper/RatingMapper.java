package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    RatingDTO ratingToRatingDTO(Rating rating);
    Rating ratingDTOToRating(RatingDTO ratingDTO);
}
