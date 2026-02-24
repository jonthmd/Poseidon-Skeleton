package com.nnk.springboot.services;

import com.nnk.springboot.dto.BidListDTO;

import java.util.List;

public interface BidListService {

    List<BidListDTO> findAllBids();

    BidListDTO addBidList(BidListDTO bidListDTO);

    BidListDTO getBidList(Integer id);

    BidListDTO updateBidList(BidListDTO bidListDTO);

    void deleteBidList(Integer id);
}
