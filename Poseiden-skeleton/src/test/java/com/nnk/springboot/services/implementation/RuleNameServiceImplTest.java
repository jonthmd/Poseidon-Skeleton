package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.mapper.RuleNameMapper;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RuleNameServiceImplTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @Mock
    private RuleNameMapper ruleNameMapper;

    @InjectMocks
    private RuleNameServiceImpl classUnderTest;

    @Test
    void findAllRuleNames() {

        //GIVEN
        RuleName ruleName = new RuleName();
        List<RuleName> ruleNames = List.of(ruleName);

        RuleNameDTO ruleNameDTO = new RuleNameDTO();
        List<RuleNameDTO> ruleNameDTOS = List.of(ruleNameDTO);

        when(ruleNameRepository.findAll()).thenReturn(ruleNames);
        when(ruleNameMapper.ruleNameToRuleNameDTO(ruleName)).thenReturn(ruleNameDTO);

        List<RuleNameDTO> result = classUnderTest.findAllRuleNames();

        //THEN
        verify(ruleNameRepository).findAll();
        verify(ruleNameMapper).ruleNameToRuleNameDTO(ruleName);
        assertThat(result).isEqualTo(ruleNameDTOS);
    }

    @Test
    void addRuleName() {

        // GIVEN
        RuleName ruleName = new RuleName();
        RuleNameDTO ruleNameDTO = new RuleNameDTO();

        when(ruleNameMapper.ruleNameDTOToRuleName(ruleNameDTO)).thenReturn(ruleName);
        when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
        when(ruleNameMapper.ruleNameToRuleNameDTO(ruleName)).thenReturn(ruleNameDTO);

        //WHEN
        RuleNameDTO result = classUnderTest.addRuleName(ruleNameDTO);

        //THEN
        verify(ruleNameRepository).save(ruleName);
        verify(ruleNameMapper).ruleNameDTOToRuleName(ruleNameDTO);
        verify(ruleNameMapper).ruleNameToRuleNameDTO(ruleName);
        assertThat(result).isEqualTo(ruleNameDTO);
    }

    @Test
    void getRuleName() {

        // GIVEN
        RuleName ruleName = new RuleName();
        RuleNameDTO ruleNameDTO = new RuleNameDTO();

        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));
        when(ruleNameMapper.ruleNameToRuleNameDTO(ruleName)).thenReturn(ruleNameDTO);

        //WHEN
        RuleNameDTO result = classUnderTest.getRuleName(1);

        //THEN
        verify(ruleNameRepository).findById(1);
        verify(ruleNameMapper).ruleNameToRuleNameDTO(ruleName);
        assertThat(result).isEqualTo(ruleNameDTO);
    }

    @Test
    void getRuleNameException() {

        // GIVEN
        when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.getRuleName(1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateRuleName() {

        // GIVEN
        RuleName entity = new RuleName();
        RuleNameDTO dto = new RuleNameDTO();

        when(ruleNameMapper.ruleNameDTOToRuleName(dto)).thenReturn(entity);
        when(ruleNameRepository.save(entity)).thenReturn(entity);
        when(ruleNameMapper.ruleNameToRuleNameDTO(entity)).thenReturn(dto);

        //WHEN
        RuleNameDTO result = classUnderTest.updateRuleName(dto);

        //THEN
        verify(ruleNameRepository).save(entity);
        verify(ruleNameMapper).ruleNameDTOToRuleName(dto);
        verify(ruleNameMapper).ruleNameToRuleNameDTO(entity);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void deleteRuleName() {

        //GIVEN
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        //WHEN
        classUnderTest.deleteRuleName(1);

        //THEN
        verify(ruleNameRepository).findById(1);
        verify(ruleNameRepository).deleteById(1);
    }

    @Test
    void deleteRuleNameException() {

        //GIVEN
        when(ruleNameRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.deleteRuleName(1)).isInstanceOf(RuntimeException.class);
    }
}