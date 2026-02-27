package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating/list")
    public String homeRating(Model model) {

        model.addAttribute("ratings", ratingService.findAllRatings());

        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Model model) {

        model.addAttribute("rating", new RatingDTO());

        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validateRating(@Valid @ModelAttribute("rating") RatingDTO ratingDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "rating/add";
        }

        ratingService.addRating(ratingDTO);

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateRatingForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("rating", ratingService.getRating(id));

        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") RatingDTO ratingDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "rating/update";
        }

        ratingService.updateRating(id, ratingDTO);

        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {

        ratingService.deleteRating(id);

        return "redirect:/rating/list";
    }
}
