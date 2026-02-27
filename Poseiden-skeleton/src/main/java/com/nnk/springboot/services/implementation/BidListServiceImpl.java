package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.mapper.BidListMapper;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;
    private final BidListMapper bidListMapper;

    public BidListServiceImpl(BidListRepository bidListRepository, BidListMapper bidListMapper) {
        this.bidListRepository = bidListRepository;
        this.bidListMapper = bidListMapper;
    }

    @Override
    public List<BidListDTO> findAllBids() {

        return bidListRepository.findAll()
                .stream()
                .map(bidListMapper::BidListToBidListDTO)
                .toList();
    }

    @Override
    public BidListDTO addBidList(BidListDTO bidListDTO) {

        BidList bidList = bidListMapper.BidListDTOToBidList(bidListDTO);
        BidList saved = bidListRepository.save(bidList);

        return bidListMapper.BidListToBidListDTO(saved);
    }

    @Override
    public BidListDTO getBidList(Integer id) {

        BidList bidList = bidListRepository.findById(id).orElse(null);

        if (bidList == null) {
            throw new RuntimeException("BidList not found.");
        }

        return bidListMapper.BidListToBidListDTO(bidList);
    }

    @Override
    public BidListDTO updateBidList(Integer id, BidListDTO bidListDTO) {

        BidList bidList = bidListRepository.findById(id).orElse(null);

        if (bidList == null) {
            throw new RuntimeException("BidList not found.");
        }
        bidList.setAccount(bidListDTO.getAccount());
        bidList.setType(bidListDTO.getType());
        bidList.setBidQuantity(bidListDTO.getBidQuantity());

        BidList updated = bidListRepository.save(bidList);

        return bidListMapper.BidListToBidListDTO(updated);
    }

    @Override
    public void deleteBidList(Integer id) {

        BidList bidList = bidListRepository.findById(id).orElse(null);

        if (bidList == null) {
            throw new RuntimeException("BidList not found.");
        }

        bidListRepository.deleteById(id);
    }
}
