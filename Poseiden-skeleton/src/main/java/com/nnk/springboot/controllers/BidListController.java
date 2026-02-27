package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.services.BidListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BidListController {

    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String homeBid(Model model) {

        model.addAttribute("bidLists", bidListService.findAllBids());

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {

        model.addAttribute("bidList", new BidListDTO());

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validateBid(@Valid @ModelAttribute("bidList") BidListDTO bidListDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "bidList/add";
        }

        bidListService.addBidList(bidListDTO);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateBidForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("bidList", bidListService.getBidList(id));

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bidList") BidListDTO bidListDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "bidList/update";
        }

        bidListService.updateBidList(id, bidListDTO);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {

        bidListService.deleteBidList(id);

        return "redirect:/bidList/list";
    }
}
