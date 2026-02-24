package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.mapper.RatingMapper;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RatingMapper ratingMapper;

    @InjectMocks
    private RatingServiceImpl classUnderTest;

    @Test
    void findAllRatings() {

        // GIVEN
        Rating rating = new Rating();
        List<Rating> ratings = List.of(rating);

        RatingDTO ratingDTO = new RatingDTO();
        List<RatingDTO> ratingDTOs = List.of(ratingDTO);

        when(ratingRepository.findAll()).thenReturn(ratings);
        when(ratingMapper.ratingToRatingDTO(rating)).thenReturn(ratingDTO);

        // WHEN
        List<RatingDTO> result = classUnderTest.findAllRatings();

        // THEN
        verify(ratingRepository).findAll();
        verify(ratingMapper).ratingToRatingDTO(rating);
        assertThat(result).isEqualTo(ratingDTOs);
    }

    @Test
    void addRating() {

        // GIVEN
        Rating rating = new Rating();
        RatingDTO ratingDTO = new RatingDTO();

        when(ratingMapper.ratingDTOToRating(ratingDTO)).thenReturn(rating);
        when(ratingRepository.save(rating)).thenReturn(rating);
        when(ratingMapper.ratingToRatingDTO(rating)).thenReturn(ratingDTO);

        // WHEN
        RatingDTO result = classUnderTest.addRating(ratingDTO);

        // THEN
        verify(ratingRepository).save(rating);
        verify(ratingMapper).ratingToRatingDTO(rating);
        verify(ratingMapper).ratingDTOToRating(ratingDTO);
        assertThat(result).isEqualTo(ratingDTO);
    }

    @Test
    void getRating() {

        // GIVEN
        Rating rating = new Rating();
        RatingDTO ratingDTO = new RatingDTO();

        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));
        when(ratingMapper.ratingToRatingDTO(rating)).thenReturn(ratingDTO);

        // WHEN
        RatingDTO result = classUnderTest.getRating(1);

        // THEN
        verify(ratingRepository).findById(1);
        verify(ratingMapper).ratingToRatingDTO(rating);
        assertThat(result).isEqualTo(ratingDTO);
    }

    @Test
    void getRatingException() {

        //GIVEN
        when(ratingRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.getRating(1))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateRating() {

        //GIVEN
        Rating rating = new Rating();
        RatingDTO ratingDTO = new RatingDTO();

        when(ratingMapper.ratingDTOToRating(ratingDTO)).thenReturn(rating);
        when(ratingRepository.save(rating)).thenReturn(rating);
        when(ratingMapper.ratingToRatingDTO(rating)).thenReturn(ratingDTO);

        //WHEN
        RatingDTO result = classUnderTest.updateRating(ratingDTO);

        //THEN
        verify(ratingRepository).save(rating);
        verify(ratingMapper).ratingToRatingDTO(rating);
        verify(ratingMapper).ratingDTOToRating(ratingDTO);
        assertThat(result).isEqualTo(ratingDTO);
    }

    @Test
    void deleteRating() {

        //GIVEN
        Rating rating = new Rating();
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        //WHEN
        classUnderTest.deleteRating(1);

        //THEN
        verify(ratingRepository).findById(1);
        verify(ratingRepository).deleteById(1);
    }

    @Test
    void deleteRatingException() {

        //GIVEN
        when(ratingRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.deleteRating(1)).isInstanceOf(RuntimeException.class);
    }
}