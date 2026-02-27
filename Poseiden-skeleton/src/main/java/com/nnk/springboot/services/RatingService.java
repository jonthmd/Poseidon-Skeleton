package com.nnk.springboot.services;

import com.nnk.springboot.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    List<RatingDTO> findAllRatings();

    RatingDTO addRating(RatingDTO ratingDTO);

    RatingDTO getRating(Integer id);

    RatingDTO updateRating(Integer id, RatingDTO ratingDTO);

    void deleteRating(Integer id);
}
