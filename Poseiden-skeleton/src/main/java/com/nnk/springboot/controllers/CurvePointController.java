package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.services.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurvePointController {

    private final CurvePointService curvePointService;

    public CurvePointController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String homeCurvePoint(Model model) {

        model.addAttribute("curvePoints", curvePointService.findAllCurvePoints());

        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(Model model) {

        model.addAttribute("curvePoint", new CurvePointDTO());

        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validateCurvePoint(@Valid @ModelAttribute("curvePoint") CurvePointDTO curvePointDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "curvePoint/add";
        }

        curvePointService.addCurvePoint(curvePointDTO);

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateCurvePointForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("curvePoint", curvePointService.getCurvePoint(id));

        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid @ModelAttribute("curvePoint") CurvePointDTO curvePointDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "curvePoint/update";
        }

        curvePointService.updateCurvePoint(id, curvePointDTO);

        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id) {

        curvePointService.deleteCurvePoint(id);

        return "redirect:/curvePoint/list";
    }
}
