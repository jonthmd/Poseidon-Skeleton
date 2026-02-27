package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.mapper.TradeMapper;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;
    private final TradeMapper tradeMapper;

    public TradeServiceImpl(TradeRepository tradeRepository, TradeMapper tradeMapper) {
        this.tradeRepository = tradeRepository;
        this.tradeMapper = tradeMapper;
    }

    @Override
    public List<TradeDTO> findAllTrades() {

        return tradeRepository.findAll()
                .stream()
                .map(tradeMapper::tradeToTradeDTO)
                .toList();
    }

    @Override
    public TradeDTO addTrade(TradeDTO tradeDTO) {

        Trade trade = tradeMapper.tradeDTOToTrade(tradeDTO);
        Trade saved = tradeRepository.save(trade);

        return tradeMapper.tradeToTradeDTO(saved);
    }

    @Override
    public TradeDTO getTrade(Integer id) {

        Trade trade = tradeRepository.findById(id).orElse(null);

        if (trade == null) {
            throw new RuntimeException("Trade not found");
        }

        return tradeMapper.tradeToTradeDTO(trade);
    }

    @Override
    public TradeDTO updateTrade(Integer id, TradeDTO tradeDTO) {

        Trade trade = tradeRepository.findById(id).orElse(null);

        if (trade == null) {
            throw new RuntimeException("Trade not found");
        }

        trade.setType(tradeDTO.getType());
        trade.setBuyQuantity(tradeDTO.getBuyQuantity());
        trade.setAccount(tradeDTO.getAccount());

        Trade updated = tradeRepository.save(trade);

        return tradeMapper.tradeToTradeDTO(updated);
    }

    @Override
    public void deleteTrade(Integer id) {

        Trade trade = tradeRepository.findById(id).orElse(null);

        if (trade == null) {
            throw new RuntimeException("Trade not found");
        }

        tradeRepository.deleteById(id);
    }
}
