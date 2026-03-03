package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.exceptions.DeleteAdminException;
import com.nnk.springboot.exceptions.UpdateAdminException;
import com.nnk.springboot.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/list")
    public String home(Model model) {

        model.addAttribute("users", userService.findAllUsers());

        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUserForm(Model model) {

        model.addAttribute("user", new UserDTO());

        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validateUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result) {

        if (result.hasErrors()) {
            return "user/add";
        }
        userService.addUser(userDTO);

        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("user", userService.getUser(id));

        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "user/update";
        }

        try {
            userService.updateUser(id, userDTO);

            return "redirect:/user/list";

        } catch (UpdateAdminException e) {
            model.addAttribute("error", e.getMessage());

            return "user/update";
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

        try {
            userService.deleteUser(id);
        } catch (DeleteAdminException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/user/list";
    }
}
