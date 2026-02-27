package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.mapper.CurvePointMapper;
import com.nnk.springboot.repositories.CurvePointRepository;
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
class CurvePointServiceImplTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @Mock
    private CurvePointMapper curvePointMapper;

    @InjectMocks
    private CurvePointServiceImpl classUnderTest;

    @Test
    void findAllCurvePoints() {

        //GIVEN
        CurvePoint  curvePoint = new CurvePoint();
        List<CurvePoint> curvePoints = List.of(curvePoint);

        CurvePointDTO curvePointDTO = new CurvePointDTO();
        List<CurvePointDTO> curvePointDTOs = List.of(curvePointDTO);

        when(curvePointRepository.findAll()).thenReturn(curvePoints);
        when(curvePointMapper.curvePointToCurvePointDTO(curvePoint)).thenReturn(curvePointDTO);

        //WHEN
        List<CurvePointDTO> result = classUnderTest.findAllCurvePoints();

        //THEN
        verify(curvePointRepository).findAll();
        verify(curvePointMapper).curvePointToCurvePointDTO(curvePoint);
        assertThat(result).isEqualTo(curvePointDTOs);
    }

    @Test
    void addCurvePoint() {

        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        CurvePointDTO curvePointDTO = new CurvePointDTO();

        when(curvePointMapper.curvePointDTOToCurvePoint(curvePointDTO)).thenReturn(curvePoint);
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
        when(curvePointMapper.curvePointToCurvePointDTO(curvePoint)).thenReturn(curvePointDTO);

        //WHEN
        CurvePointDTO result = classUnderTest.addCurvePoint(curvePointDTO);

        //THEN
        verify(curvePointRepository).save(curvePoint);
        verify(curvePointMapper).curvePointToCurvePointDTO(curvePoint);
        verify(curvePointMapper).curvePointDTOToCurvePoint(curvePointDTO);
        assertThat(result).isEqualTo(curvePointDTO);
    }

    @Test
    void getCurvePoint() {

        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        CurvePointDTO curvePointDTO = new CurvePointDTO();

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        when(curvePointMapper.curvePointToCurvePointDTO(curvePoint)).thenReturn(curvePointDTO);

        //WHEN
        CurvePointDTO result = classUnderTest.getCurvePoint(1);

        //THEN
        verify(curvePointRepository).findById(1);
        verify(curvePointMapper).curvePointToCurvePointDTO(curvePoint);
        assertThat(result).isEqualTo(curvePointDTO);
    }

    @Test
    void getCurvePointException() {

        //GIVEN
        when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.getCurvePoint(1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateCurvePoint() {

        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        CurvePointDTO curvePointDTO = new CurvePointDTO();

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);
        when(curvePointMapper.curvePointToCurvePointDTO(curvePoint)).thenReturn(curvePointDTO);

        //WHEN
        CurvePointDTO result = classUnderTest.updateCurvePoint(1, curvePointDTO);

        //THEN
        verify(curvePointRepository).save(curvePoint);
        verify(curvePointMapper).curvePointToCurvePointDTO(curvePoint);
        assertThat(result).isEqualTo(curvePointDTO);
    }

    @Test
    void updateCurvePointException() {

        //GIVEN
        CurvePointDTO curvePointDTO = new CurvePointDTO();
        when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.updateCurvePoint(1, curvePointDTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteCurvePoint() {

        //GIVEN
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

        //WHEN
        classUnderTest.deleteCurvePoint(1);

        //THEN
        verify(curvePointRepository).findById(1);
        verify(curvePointRepository).deleteById(1);
    }

    @Test
    void deleteCurvePointException() {

        //GIVEN
        when(curvePointRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.deleteCurvePoint(1)).isInstanceOf(RuntimeException.class);
    }


}