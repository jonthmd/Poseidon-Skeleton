package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TradeMapper {

    TradeDTO tradeToTradeDTO(Trade trade);
    Trade tradeDTOToTrade(TradeDTO tradeDTO);
}
