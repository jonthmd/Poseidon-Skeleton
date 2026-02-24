package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BidListMapper {

    BidListDTO BidListToBidListDTO(BidList bidList);
    BidList BidListDTOToBidList(BidListDTO bidListDTO);
}
