package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-28T17:07:52+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Oracle Corporation)"
)
@Component
public class CurvePointMapperImpl implements CurvePointMapper {

    @Override
    public CurvePointDTO curvePointToCurvePointDTO(CurvePoint curvePoint) {
        if ( curvePoint == null ) {
            return null;
        }

        CurvePointDTO curvePointDTO = new CurvePointDTO();

        curvePointDTO.setId( curvePoint.getId() );
        curvePointDTO.setCurveId( curvePoint.getCurveId() );
        curvePointDTO.setTerm( curvePoint.getTerm() );
        curvePointDTO.setValue( curvePoint.getValue() );

        return curvePointDTO;
    }

    @Override
    public CurvePoint curvePointDTOToCurvePoint(CurvePointDTO curvePointDTO) {
        if ( curvePointDTO == null ) {
            return null;
        }

        CurvePoint curvePoint = new CurvePoint();

        curvePoint.setId( curvePointDTO.getId() );
        curvePoint.setCurveId( curvePointDTO.getCurveId() );
        curvePoint.setTerm( curvePointDTO.getTerm() );
        curvePoint.setValue( curvePointDTO.getValue() );

        return curvePoint;
    }
}
