package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.services.BidListService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;


@Controller
public class BidListController {
    // TODO: Inject Bid service
    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        // TODO: call service find all bids to show to the view

        model.addAttribute("bidLists", bidListService.findAll());

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(Model model) {

        model.addAttribute("bidList", new BidListDTO());

        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidListDTO bidListDTO, BindingResult result) {
        // TODO: check data valid and save to db, after saving return bidListDTO list
        if (result.hasErrors()) {

            return "bidList/add";
        }

        bidListService.addBidList(bidListDTO);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        model.addAttribute("bidList",bidListService.getBidList(id));

        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bidList") BidListDTO bidListDTO, BindingResult result) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {

            return "bidList/update";
        }
        bidListService.getBidList(id);
        bidListService.updateBidList(bidListDTO);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteBidList(id);

        return "redirect:/bidList/list";
    }
}
