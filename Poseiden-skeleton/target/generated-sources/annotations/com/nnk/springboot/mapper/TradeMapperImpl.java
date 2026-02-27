package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-27T12:43:01+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Oracle Corporation)"
)
@Component
public class TradeMapperImpl implements TradeMapper {

    @Override
    public TradeDTO tradeToTradeDTO(Trade trade) {
        if ( trade == null ) {
            return null;
        }

        TradeDTO tradeDTO = new TradeDTO();

        tradeDTO.setId( trade.getId() );
        tradeDTO.setAccount( trade.getAccount() );
        tradeDTO.setType( trade.getType() );
        tradeDTO.setBuyQuantity( trade.getBuyQuantity() );

        return tradeDTO;
    }

    @Override
    public Trade tradeDTOToTrade(TradeDTO tradeDTO) {
        if ( tradeDTO == null ) {
            return null;
        }

        Trade trade = new Trade();

        trade.setId( tradeDTO.getId() );
        trade.setAccount( tradeDTO.getAccount() );
        trade.setType( tradeDTO.getType() );
        trade.setBuyQuantity( tradeDTO.getBuyQuantity() );

        return trade;
    }
}
