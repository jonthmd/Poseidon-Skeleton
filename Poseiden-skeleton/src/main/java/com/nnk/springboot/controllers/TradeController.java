package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.services.TradeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @RequestMapping("/trade/list")
    public String homeTrade(Model model) {

        model.addAttribute("trades", tradeService.findAllTrades());

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Model model) {

        model.addAttribute("trade", new TradeDTO());

        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validateTrade(@Valid @ModelAttribute("trade") TradeDTO tradeDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "trade/add";
        }

        tradeService.addTrade(tradeDTO);

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateTradeForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("trade", tradeService.getTrade(id));

        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") TradeDTO tradeDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "trade/update";
        }

        tradeService.updateTrade(id, tradeDTO);

        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id) {

        tradeService.deleteTrade(id);

        return "redirect:/trade/list";
    }
}
