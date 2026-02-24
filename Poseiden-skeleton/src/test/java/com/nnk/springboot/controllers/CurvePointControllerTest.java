package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.services.CurvePointService;
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

@WebMvcTest(CurvePointController.class)
class CurvePointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CurvePointService curvePointService;

    @Test
    @WithMockUser
    void homeCurvePoint() throws Exception {

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoints"))
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    @WithMockUser
    void addCurvePointForm() throws Exception {

        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    @WithMockUser
    void validateCurvePoint() throws Exception {

        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf())
                        .param("curveId", "2")
                        .param("term", "2.0")
                        .param("value", "2.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    @WithMockUser
    void validateCurvePointError() throws Exception {

        mockMvc.perform(post("/curvePoint/validate")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    @WithMockUser
    void showUpdateCurvePointForm() throws Exception {

        CurvePointDTO curvePointDTO = new CurvePointDTO(1, 1, 1.0, 1.0);
        when(curvePointService.getCurvePoint(1)).thenReturn(curvePointDTO);

        mockMvc.perform(get("/curvePoint/update/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    @WithMockUser
    void updateCurvePoint() throws Exception {

        CurvePointDTO curvePointDTO = new CurvePointDTO(1, 1, 1.0, 1.0);
        when(curvePointService.getCurvePoint(1)).thenReturn(curvePointDTO);

        mockMvc.perform(post("/curvePoint/update/{id}", 1)
                        .with(csrf())
                        .param("curveId", "2")
                        .param("term", "2.0")
                        .param("value", "2.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    @WithMockUser
    void updateCurvePointError() throws Exception {

        mockMvc.perform(post("/curvePoint/update/{id}", 1)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    @WithMockUser
    void deleteCurvePoint() throws Exception {

        CurvePointDTO curvePointDTO = new CurvePointDTO(1, 1, 1.0, 1.0);
        when(curvePointService.getCurvePoint(1)).thenReturn(curvePointDTO);

        mockMvc.perform(get("/curvePoint/delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }
}