package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.services.RuleNameService;
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

@WebMvcTest(RuleNameController.class)
class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RuleNameService ruleNameService;

    @Test
    @WithMockUser
    void homeRuleName() throws Exception {

        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleNames"))
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    @WithMockUser
    void addRuleNameForm() throws Exception {

        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"))
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    @WithMockUser
    void validateRuleName() throws Exception {

        mockMvc.perform(post("/ruleName/validate")
                        .with(csrf())
                        .param("name", "Test")
                        .param("description", "Desc")
                        .param("json", "{}")
                        .param("template", "Temp")
                        .param("sql", "SQL")
                        .param("sqlPart", "Part"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    @WithMockUser
    void validateRuleNameError() throws Exception {

        mockMvc.perform(post("/ruleName/validate")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));
    }

    @Test
    @WithMockUser
    void showUpdateRuleNameForm() throws Exception {

        RuleNameDTO dto = new RuleNameDTO(1, "Test", "Desc", "{}", "Temp", "SQL", "Part");
        when(ruleNameService.getRuleName(1)).thenReturn(dto);

        mockMvc.perform(get("/ruleName/update/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"))
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    @WithMockUser
    void updateRuleName() throws Exception {

        RuleNameDTO dto = new RuleNameDTO(1, "Test", "Desc", "{}", "Temp", "SQL", "Part");
        when(ruleNameService.getRuleName(1)).thenReturn(dto);

        mockMvc.perform(post("/ruleName/update/{id}", 1)
                        .with(csrf())
                        .param("name", "Updated")
                        .param("description", "Desc")
                        .param("json", "{}")
                        .param("template", "Temp")
                        .param("sql", "SQL")
                        .param("sqlPart", "Part"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    @WithMockUser
    void updateRuleNameError() throws Exception {

        mockMvc.perform(post("/ruleName/update/{id}", 1)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    @WithMockUser
    void deleteRuleName() throws Exception {

        RuleNameDTO dto = new RuleNameDTO(1, "Test", "Desc", "{}", "Temp", "SQL", "Part");
        when(ruleNameService.getRuleName(1)).thenReturn(dto);

        mockMvc.perform(get("/ruleName/delete/{id}", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }
}
