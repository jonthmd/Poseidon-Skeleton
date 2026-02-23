package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.mapper.BidListMapper;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BidListServiceImplTest {

    @Mock
    private BidListRepository bidListRepository;

    @Mock
    private BidListMapper bidListMapper;

    @InjectMocks
    private BidListServiceImpl classUnderTest;

    @Test
    void findAll() {

        //GIVEN
        BidList  bidList = new BidList();
        List<BidList> bidLists = List.of(bidList);

        BidListDTO bidListDTO = new BidListDTO();
        List<BidListDTO> bidListDTOs = List.of(bidListDTO);

        when(bidListRepository.findAll()).thenReturn(bidLists);
        when(bidListMapper.BidListToBidListDTO(bidList)).thenReturn(bidListDTO);

        //WHEN
        List<BidListDTO> result = classUnderTest.findAll();

        //THEN
        verify(bidListRepository).findAll();
        verify(bidListMapper).BidListToBidListDTO(bidList);
        assertThat(result).isEqualTo(bidListDTOs);
    }

    @Test
    void addBidList() {

        //GIVEN
        BidList bidList = new BidList();
        BidListDTO bidListDTO = new BidListDTO();

        when(bidListMapper.BidListDTOToBidList(bidListDTO)).thenReturn(bidList);
        when(bidListRepository.save(bidList)).thenReturn(bidList);
        when(bidListMapper.BidListToBidListDTO(bidList)).thenReturn(bidListDTO);

        //WHEN
        BidListDTO result = classUnderTest.addBidList(bidListDTO);

        //THEN
        verify(bidListRepository).save(bidList);
        verify(bidListMapper).BidListToBidListDTO(bidList);
        verify(bidListMapper).BidListDTOToBidList(bidListDTO);
        assertThat(result).isEqualTo(bidListDTO);
    }

    @Test
    void updateBidList() {

        //GIVEN
        BidList bidList = new BidList();
        BidListDTO bidListDTO = new BidListDTO();

        when(bidListMapper.BidListDTOToBidList(bidListDTO)).thenReturn(bidList);
        when(bidListRepository.save(bidList)).thenReturn(bidList);
        when(bidListMapper.BidListToBidListDTO(bidList)).thenReturn(bidListDTO);

        //WHEN
        BidListDTO result = classUnderTest.updateBidList(bidListDTO);

        //THEN
        verify(bidListRepository).save(bidList);
        verify(bidListMapper).BidListToBidListDTO(bidList);
        verify(bidListMapper).BidListDTOToBidList(bidListDTO);
        assertThat(result).isEqualTo(bidListDTO);
    }

    @Test
    void deleteBidList() {

        //GIVEN
        BidList bidList = new BidList();
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));

        //WHEN
        classUnderTest.deleteBidList(1);

        //THEN
        verify(bidListRepository).findById(1);
        verify(bidListRepository).deleteById(1);
    }

    @Test
    void deleteBidListException() {

        //GIVEN
        when(bidListRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.deleteBidList(1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void getBidList() {

        //GIVEN
        BidList bidList = new BidList();
        BidListDTO bidListDTO = new BidListDTO();

        when(bidListRepository.findById(1)).thenReturn(Optional.of(bidList));
        when(bidListMapper.BidListToBidListDTO(bidList)).thenReturn(bidListDTO);

        //WHEN
        BidListDTO result = classUnderTest.getBidList(1);

        //THEN
        verify(bidListRepository).findById(1);
        verify(bidListMapper).BidListToBidListDTO(bidList);
        assertThat(result).isEqualTo(bidListDTO);
    }

    @Test
    void getBidListException() {

        //GIVEN
        when(bidListRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.getBidList(1)).isInstanceOf(RuntimeException.class);
    }
}