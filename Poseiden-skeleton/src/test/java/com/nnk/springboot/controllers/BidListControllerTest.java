package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.services.BidListService;
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

@WebMvcTest(BidListController.class)
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BidListService bidListService;

    @Test
    @WithMockUser
    void homeBid() throws Exception {

        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidLists"))
                .andExpect(view().name("bidList/list"));
    }

    @Test
    @WithMockUser
    void addBidForm() throws Exception {

        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"))
                .andExpect(view().name("bidList/add"));
    }

    @Test
    @WithMockUser
    void validateBid() throws Exception {

        mockMvc.perform(post("/bidList/validate")
                        .with(csrf())
                        .param("account", "account")
                        .param("type", "type")
                        .param("bidQuantity", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    @WithMockUser
    void validateBidError() throws Exception {

        mockMvc.perform(post("/bidList/validate")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    @WithMockUser
    void showUpdateBidForm() throws Exception {

        BidListDTO bidListDTO = new BidListDTO(1, "Account", "type", 1.0);
        when(bidListService.getBidList(1)).thenReturn(bidListDTO);

        mockMvc.perform(get("/bidList/update/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"))
                .andExpect(view().name("bidList/update"));
    }

    @Test
    @WithMockUser
    void updateBid() throws Exception {

        BidListDTO bidListDTO = new BidListDTO(1, "Account", "type", 1.0);
        when(bidListService.getBidList(1)).thenReturn(bidListDTO);

        mockMvc.perform(post("/bidList/update/{id}", 1)
                        .with(csrf())
                        .param("account", "account")
                        .param("type", "type")
                        .param("bidQuantity", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    @WithMockUser
    void updateBidError() throws Exception {

        mockMvc.perform(post("/bidList/update/{id}", 1)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    @WithMockUser
    void deleteBid() throws Exception {

        BidListDTO bidListDTO = new BidListDTO(1, "Account", "type", 1.0);
        when(bidListService.getBidList(1)).thenReturn(bidListDTO);

        mockMvc.perform(get("/bidList/delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }
}