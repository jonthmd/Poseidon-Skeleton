package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.UserDTO;
import com.nnk.springboot.services.UserService;
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

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @WithMockUser
    void homeUser() throws Exception {

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("user/list"));
    }

    @Test
    @WithMockUser
    void addUserForm() throws Exception {

        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/add"));
    }

    @Test
    @WithMockUser
    void validateUser() throws Exception {

        mockMvc.perform(post("/user/validate")
                        .with(csrf())
                        .param("username", "jon")
                        .param("password", "Jon1234!")
                        .param("fullname", "Jon")
                        .param("role", "USER"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    @WithMockUser
    void validateUserError() throws Exception {

        mockMvc.perform(post("/user/validate")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    @WithMockUser
    void showUpdateUserForm() throws Exception {

        UserDTO dto = new UserDTO(1, "jon", "Jon1234!", "Jon", "USER");
        when(userService.getUser(1)).thenReturn(dto);

        mockMvc.perform(get("/user/update/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/update"));
    }

    @Test
    @WithMockUser
    void updateUser() throws Exception {

        UserDTO dto = new UserDTO(1, "jon", "Jon1234!", "Jon", "USER");
        when(userService.getUser(1)).thenReturn(dto);

        mockMvc.perform(post("/user/update/{id}", 1)
                        .with(csrf())
                        .param("username", "Noj")
                        .param("password", "Noj1234!")
                        .param("fullname", "Noj")
                        .param("role", "USER"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    @WithMockUser
    void updateUserError() throws Exception {

        mockMvc.perform(post("/user/update/{id}", 1)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }

    @Test
    @WithMockUser
    void deleteUser() throws Exception {

        UserDTO dto = new UserDTO(1, "jon", "Jon1234!", "Jon", "USER");
        when(userService.getUser(1)).thenReturn(dto);

        mockMvc.perform(get("/user/delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }
}