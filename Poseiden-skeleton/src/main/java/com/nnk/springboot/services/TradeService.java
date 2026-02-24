package com.nnk.springboot.services;

import com.nnk.springboot.dto.TradeDTO;

import java.util.List;

public interface TradeService {

    List<TradeDTO> findAllTrades();

    TradeDTO addTrade(TradeDTO tradeDTO);

    TradeDTO getTrade(Integer id);

    TradeDTO updateTrade(TradeDTO tradeDTO);

    void deleteTrade(Integer id);
}
