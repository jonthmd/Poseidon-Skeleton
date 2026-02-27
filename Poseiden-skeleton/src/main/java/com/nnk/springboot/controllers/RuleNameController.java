package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.services.RuleNameService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RuleNameController {

    private final RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @RequestMapping("/ruleName/list")
    public String homeRuleName(Model model) {

        model.addAttribute("ruleNames", ruleNameService.findAllRuleNames());

        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleNameForm(Model model) {

        model.addAttribute("ruleName", new RuleNameDTO());

        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validateRuleName(@Valid @ModelAttribute("ruleName") RuleNameDTO ruleNameDTO,
                                   BindingResult result) {

        if (result.hasErrors()) {
            return "ruleName/add";
        }

        ruleNameService.addRuleName(ruleNameDTO);

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateRuleNameForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("ruleName", ruleNameService.getRuleName(id));

        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleNameDTO ruleNameDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleNameService.updateRuleName(id, ruleNameDTO);

        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id) {

        ruleNameService.deleteRuleName(id);

        return "redirect:/ruleName/list";
    }
}
