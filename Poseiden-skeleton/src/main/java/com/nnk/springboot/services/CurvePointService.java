package com.nnk.springboot.services;

import com.nnk.springboot.dto.CurvePointDTO;

import java.util.List;

public interface CurvePointService {

    List<CurvePointDTO> findAllCurvePoints();

    CurvePointDTO addCurvePoint(CurvePointDTO curvePointDTO);

    CurvePointDTO getCurvePoint(Integer id);

    CurvePointDTO updateCurvePoint(CurvePointDTO curvePointDTO);

    void deleteCurvePoint(Integer id);
}
