package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RuleNameMapper {

    RuleNameDTO ruleNameToRuleNameDTO(RuleName ruleName);
    RuleName ruleNameDTOToRuleName(RuleNameDTO ruleNameDTO);
}
