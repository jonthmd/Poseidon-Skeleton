package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.mapper.RatingMapper;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public List<RatingDTO> findAllRatings() {

        return ratingRepository.findAll()
                .stream()
                .map(ratingMapper::ratingToRatingDTO)
                .toList();
    }

    @Override
    public RatingDTO addRating(RatingDTO ratingDTO) {

        Rating rating = ratingMapper.ratingDTOToRating(ratingDTO);
        Rating saved = ratingRepository.save(rating);

        return ratingMapper.ratingToRatingDTO(saved);
    }

    @Override
    public RatingDTO getRating(Integer id) {

        Rating rating = ratingRepository.findById(id).orElse(null);

        if (rating == null) {
            throw new RuntimeException("Rating not found");
        }

        return ratingMapper.ratingToRatingDTO(rating);
    }

    @Override
    public RatingDTO updateRating(Integer id, RatingDTO ratingDTO) {

        Rating rating = ratingRepository.findById(id).orElse(null);

        if (rating == null) {
            throw new RuntimeException("Rating not found");
        }

        rating.setMoodysRating(ratingDTO.getMoodysRating());
        rating.setFitchRating(ratingDTO.getFitchRating());
        rating.setOrder(ratingDTO.getOrder());
        rating.setFitchRating(ratingDTO.getFitchRating());

        Rating updated = ratingRepository.save(rating);

        return ratingMapper.ratingToRatingDTO(updated);
    }

    @Override
    public void deleteRating(Integer id) {

        Rating rating = ratingRepository.findById(id).orElse(null);

        if (rating == null) {
            throw new RuntimeException("Rating not found");
        }

        ratingRepository.deleteById(id);
    }
}
