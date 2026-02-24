package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.services.TradeService;
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

@WebMvcTest(TradeController.class)
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TradeService tradeService;

    @Test
    @WithMockUser
    void homeTrade() throws Exception {

        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("trades"))
                .andExpect(view().name("trade/list"));
    }

    @Test
    @WithMockUser
    void addTradeForm() throws Exception {

        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("trade"))
                .andExpect(view().name("trade/add"));
    }

    @Test
    @WithMockUser
    void validateTrade() throws Exception {

        mockMvc.perform(post("/trade/validate")
                        .with(csrf())
                        .param("account", "Account")
                        .param("type", "Type")
                        .param("buyQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    @WithMockUser
    void validateTradeError() throws Exception {

        mockMvc.perform(post("/trade/validate")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    @WithMockUser
    void showUpdateTradeForm() throws Exception {

        TradeDTO dto = new TradeDTO(1, "Account", "Type", 10.0);
        when(tradeService.getTrade(1)).thenReturn(dto);

        mockMvc.perform(get("/trade/update/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("trade"))
                .andExpect(view().name("trade/update"));
    }

    @Test
    @WithMockUser
    void updateTrade() throws Exception {

        TradeDTO dto = new TradeDTO(1, "Account", "Type", 10.0);
        when(tradeService.getTrade(1)).thenReturn(dto);

        mockMvc.perform(post("/trade/update/{id}", 1)
                        .with(csrf())
                        .param("account", "Updated")
                        .param("type", "Type")
                        .param("buyQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    @WithMockUser
    void updateTradeError() throws Exception {

        mockMvc.perform(post("/trade/update/{id}", 1)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }

    @Test
    @WithMockUser
    void deleteTrade() throws Exception {

        TradeDTO dto = new TradeDTO(1, "Account", "Type", 10.0);
        when(tradeService.getTrade(1)).thenReturn(dto);

        mockMvc.perform(get("/trade/delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }
}