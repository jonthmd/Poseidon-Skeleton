package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.mapper.RuleNameMapper;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleNameServiceImpl implements RuleNameService {

    private final RuleNameRepository ruleNameRepository;
    private final RuleNameMapper ruleNameMapper;

    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository, RuleNameMapper ruleNameMapper) {
        this.ruleNameRepository = ruleNameRepository;
        this.ruleNameMapper = ruleNameMapper;
    }

    @Override
    public List<RuleNameDTO> findAllRuleNames() {

        return ruleNameRepository.findAll()
                .stream()
                .map(ruleNameMapper::ruleNameToRuleNameDTO)
                .toList();
    }

    @Override
    public RuleNameDTO addRuleName(RuleNameDTO ruleNameDTO) {

        RuleName ruleName = ruleNameMapper.ruleNameDTOToRuleName(ruleNameDTO);
        RuleName saved = ruleNameRepository.save(ruleName);

        return ruleNameMapper.ruleNameToRuleNameDTO(saved);
    }

    @Override
    public RuleNameDTO getRuleName(Integer id) {

        RuleName ruleName = ruleNameRepository.findById(id).orElse(null);

        if (ruleName == null) {
            throw new RuntimeException("RuleName not found");
        }

        return ruleNameMapper.ruleNameToRuleNameDTO(ruleName);
    }

    @Override
    public RuleNameDTO updateRuleName(RuleNameDTO ruleNameDTO) {

        RuleName ruleName = ruleNameMapper.ruleNameDTOToRuleName(ruleNameDTO);
        RuleName updated = ruleNameRepository.save(ruleName);

        return ruleNameMapper.ruleNameToRuleNameDTO(updated);
    }

    @Override
    public void deleteRuleName(Integer id) {

        RuleName ruleName = ruleNameRepository.findById(id).orElse(null);

        if (ruleName == null) {
            throw new RuntimeException("RuleName not found");
        }

        ruleNameRepository.deleteById(id);
    }
}
