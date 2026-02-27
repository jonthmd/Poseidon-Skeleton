package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-27T12:43:01+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Oracle Corporation)"
)
@Component
public class RuleNameMapperImpl implements RuleNameMapper {

    @Override
    public RuleNameDTO ruleNameToRuleNameDTO(RuleName ruleName) {
        if ( ruleName == null ) {
            return null;
        }

        RuleNameDTO ruleNameDTO = new RuleNameDTO();

        ruleNameDTO.setId( ruleName.getId() );
        ruleNameDTO.setName( ruleName.getName() );
        ruleNameDTO.setDescription( ruleName.getDescription() );
        ruleNameDTO.setJson( ruleName.getJson() );
        ruleNameDTO.setTemplate( ruleName.getTemplate() );
        ruleNameDTO.setSql( ruleName.getSql() );
        ruleNameDTO.setSqlPart( ruleName.getSqlPart() );

        return ruleNameDTO;
    }

    @Override
    public RuleName ruleNameDTOToRuleName(RuleNameDTO ruleNameDTO) {
        if ( ruleNameDTO == null ) {
            return null;
        }

        RuleName ruleName = new RuleName();

        ruleName.setId( ruleNameDTO.getId() );
        ruleName.setName( ruleNameDTO.getName() );
        ruleName.setDescription( ruleNameDTO.getDescription() );
        ruleName.setJson( ruleNameDTO.getJson() );
        ruleName.setTemplate( ruleNameDTO.getTemplate() );
        ruleName.setSql( ruleNameDTO.getSql() );
        ruleName.setSqlPart( ruleNameDTO.getSqlPart() );

        return ruleName;
    }
}
