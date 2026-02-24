package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RatingController.class)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RatingService ratingService;

    @Test
    @WithMockUser
    void homeRating() throws Exception {

        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratings"))
                .andExpect(view().name("rating/list"));
    }

    @Test
    @WithMockUser
    void addRatingForm() throws Exception {

        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(view().name("rating/add"));
    }

    @Test
    @WithMockUser
    void validateRating() throws Exception {

        mockMvc.perform(post("/rating/validate")
                        .with(csrf())
                        .param("moodysRating", "2")
                        .param("sandPRating", "0")
                        .param("fitchRating", "2")
                        .param("orderNumber", "6"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    @WithMockUser
    void validateRatingError() throws Exception {

        mockMvc.perform(post("/rating/validate")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));
    }

    @Test
    @WithMockUser
    void showUpdateRatingForm() throws Exception {

        RatingDTO ratingDTO = new RatingDTO(1, "1", "1", "1", 1);
        when(ratingService.getRating(1)).thenReturn(ratingDTO);

        mockMvc.perform(get("/rating/update/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(view().name("rating/update"));
    }

    @Test
    @WithMockUser
    void updateRating() throws Exception {

        RatingDTO ratingDTO = new RatingDTO(1, "1", "1", "1", 1);
        when(ratingService.getRating(1)).thenReturn(ratingDTO);

        mockMvc.perform(post("/rating/update/{id}", 1)
                        .with(csrf())
                        .param("moodysRating", "2")
                        .param("sandPRating", "2")
                        .param("fitchRating", "2")
                        .param("orderNumber", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    @WithMockUser
    void updateRatingError() throws Exception {

        mockMvc.perform(post("/rating/update/{id}", 1)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    @Test
    @WithMockUser
    void deleteRating() throws Exception {

        RatingDTO ratingDTO = new RatingDTO(1, "1", "1", "1", 1);
        when(ratingService.getRating(1)).thenReturn(ratingDTO);

        mockMvc.perform(get("/rating/delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rating/list"));
    }
}