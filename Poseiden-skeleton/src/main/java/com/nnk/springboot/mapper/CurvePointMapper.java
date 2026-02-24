package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurvePointMapper {

    CurvePointDTO curvePointToCurvePointDTO(CurvePoint curvePoint);
    CurvePoint curvePointDTOToCurvePoint(CurvePointDTO curvePointDTO);
}
