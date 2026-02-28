package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-28T17:07:52+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Oracle Corporation)"
)
@Component
public class RatingMapperImpl implements RatingMapper {

    @Override
    public RatingDTO ratingToRatingDTO(Rating rating) {
        if ( rating == null ) {
            return null;
        }

        RatingDTO ratingDTO = new RatingDTO();

        ratingDTO.setId( rating.getId() );
        ratingDTO.setMoodysRating( rating.getMoodysRating() );
        ratingDTO.setSandPRating( rating.getSandPRating() );
        ratingDTO.setFitchRating( rating.getFitchRating() );
        ratingDTO.setOrder( rating.getOrder() );

        return ratingDTO;
    }

    @Override
    public Rating ratingDTOToRating(RatingDTO ratingDTO) {
        if ( ratingDTO == null ) {
            return null;
        }

        Rating rating = new Rating();

        rating.setId( ratingDTO.getId() );
        rating.setMoodysRating( ratingDTO.getMoodysRating() );
        rating.setSandPRating( ratingDTO.getSandPRating() );
        rating.setFitchRating( ratingDTO.getFitchRating() );
        rating.setOrder( ratingDTO.getOrder() );

        return rating;
    }
}
