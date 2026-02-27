package com.nnk.springboot.services;

import com.nnk.springboot.dto.RuleNameDTO;

import java.util.List;

public interface RuleNameService {

    List<RuleNameDTO> findAllRuleNames();

    RuleNameDTO addRuleName(RuleNameDTO ruleNameDTO);

    RuleNameDTO getRuleName(Integer id);

    RuleNameDTO updateRuleName(Integer id, RuleNameDTO ruleNameDTO);

    void deleteRuleName(Integer id);
}
