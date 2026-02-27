package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.mapper.CurvePointMapper;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointServiceImpl implements CurvePointService {

    private final CurvePointRepository curvePointRepository;
    private final CurvePointMapper curvePointMapper;

    public CurvePointServiceImpl(CurvePointRepository curvePointRepository, CurvePointMapper curvePointMapper) {
        this.curvePointRepository = curvePointRepository;
        this.curvePointMapper = curvePointMapper;
    }

    @Override
    public List<CurvePointDTO> findAllCurvePoints() {

        return curvePointRepository.findAll()
                .stream()
                .map(curvePointMapper::curvePointToCurvePointDTO)
                .toList();
    }

    @Override
    public CurvePointDTO addCurvePoint(CurvePointDTO curvePointDTO) {

        CurvePoint curvePoint = curvePointMapper.curvePointDTOToCurvePoint(curvePointDTO);
        CurvePoint saved = curvePointRepository.save(curvePoint);

        return curvePointMapper.curvePointToCurvePointDTO(saved);
    }

    @Override
    public CurvePointDTO getCurvePoint(Integer id) {

        CurvePoint curvePoint = curvePointRepository.findById(id).orElse(null);

        if (curvePoint == null) {
            throw new RuntimeException("Curve point not found.");
        }

        return curvePointMapper.curvePointToCurvePointDTO(curvePoint);
    }

    @Override
    public CurvePointDTO updateCurvePoint(Integer id, CurvePointDTO curvePointDTO) {

        CurvePoint curvePoint = curvePointRepository.findById(id).orElse(null);

        if (curvePoint == null) {
            throw new RuntimeException("Curve point not found.");
        }

        curvePoint.setCurveId(curvePointDTO.getCurveId());
        curvePoint.setTerm(curvePointDTO.getTerm());
        curvePoint.setValue(curvePointDTO.getValue());

        CurvePoint updated = curvePointRepository.save(curvePoint);

        return curvePointMapper.curvePointToCurvePointDTO(updated);
    }

    @Override
    public void deleteCurvePoint(Integer id) {

        CurvePoint curvePoint = curvePointRepository.findById(id).orElse(null);

        if (curvePoint == null) {
            throw new RuntimeException("Curve point not found.");
        }

        curvePointRepository.deleteById(id);
    }
}
