package com.nnk.springboot.services;

import com.nnk.springboot.dto.BidListDTO;

import java.util.List;

public interface BidListService {

    List<BidListDTO> findAll();
    BidListDTO addBidList(BidListDTO bidListDTO);
    BidListDTO updateBidList(BidListDTO bidListDTO);
    void deleteBidList(Integer id);
    BidListDTO getBidList(Integer id);
}
