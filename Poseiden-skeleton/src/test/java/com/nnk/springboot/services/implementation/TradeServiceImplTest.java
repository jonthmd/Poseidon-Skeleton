package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.mapper.TradeMapper;
import com.nnk.springboot.repositories.TradeRepository;
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
class TradeServiceImplTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeMapper tradeMapper;

    @InjectMocks
    private TradeServiceImpl classUnderTest;

    @Test
    void findAllTrades() {

        //GIVEN
        Trade trade = new Trade();
        List<Trade> trades = List.of(trade);

        TradeDTO tradeDTO = new TradeDTO();
        List<TradeDTO> tradeDTOS = List.of(tradeDTO);

        when(tradeRepository.findAll()).thenReturn(trades);
        when(tradeMapper.tradeToTradeDTO(trade)).thenReturn(tradeDTO);

        //WHEN
        List<TradeDTO> result = classUnderTest.findAllTrades();

        //THEN
        verify(tradeRepository).findAll();
        verify(tradeMapper).tradeToTradeDTO(trade);
        assertThat(result).isEqualTo(tradeDTOS);
    }

    @Test
    void addTrade() {

        //GIVEN
        Trade trade = new Trade();
        TradeDTO tradeDTO = new TradeDTO();

        when(tradeMapper.tradeDTOToTrade(tradeDTO)).thenReturn(trade);
        when(tradeRepository.save(trade)).thenReturn(trade);
        when(tradeMapper.tradeToTradeDTO(trade)).thenReturn(tradeDTO);

        //WHEN
        TradeDTO result = classUnderTest.addTrade(tradeDTO);

        //THEN
        verify(tradeRepository).save(trade);
        verify(tradeMapper).tradeDTOToTrade(tradeDTO);
        verify(tradeMapper).tradeToTradeDTO(trade);
        assertThat(result).isEqualTo(tradeDTO);
    }

    @Test
    void getTrade() {

        //GIVEN
        Trade trade = new Trade();
        TradeDTO tradeDTO = new TradeDTO();

        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        when(tradeMapper.tradeToTradeDTO(trade)).thenReturn(tradeDTO);

        //WHEN
        TradeDTO result = classUnderTest.getTrade(1);

        //THEN
        verify(tradeRepository).findById(1);
        verify(tradeMapper).tradeToTradeDTO(trade);
        assertThat(result).isEqualTo(tradeDTO);
    }

    @Test
    void getTradeException() {

        //GIVEN
        when(tradeRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.getTrade(1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void updateTrade() {

        //GIVEN
        Trade trade = new Trade();
        TradeDTO tradeDTO = new TradeDTO();

        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));
        when(tradeRepository.save(trade)).thenReturn(trade);
        when(tradeMapper.tradeToTradeDTO(trade)).thenReturn(tradeDTO);

        //WHEN
        TradeDTO result = classUnderTest.updateTrade(1, tradeDTO);

        //THEN
        verify(tradeRepository).save(trade);
        verify(tradeMapper).tradeToTradeDTO(trade);
        assertThat(result).isEqualTo(tradeDTO);
    }

    @Test
    void updateRuleNameException() {

        //GIVEN
        TradeDTO tradeDTO = new TradeDTO();
        when(tradeRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.updateTrade(1, tradeDTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void deleteTrade() {

        //GIVEN
        Trade trade = new Trade();
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        //WHEN
        classUnderTest.deleteTrade(1);

        //THEN
        verify(tradeRepository).findById(1);
        verify(tradeRepository).deleteById(1);
    }

    @Test
    void deleteTradeException() {

        //GIVEN
        when(tradeRepository.findById(1)).thenReturn(Optional.empty());

        //WHEN+THEN
        assertThatThrownBy(() -> classUnderTest.deleteTrade(1)).isInstanceOf(RuntimeException.class);
    }
}