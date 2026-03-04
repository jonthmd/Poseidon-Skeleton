package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-04T18:39:51+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Oracle Corporation)"
)
@Component
public class BidListMapperImpl implements BidListMapper {

    @Override
    public BidListDTO BidListToBidListDTO(BidList bidList) {
        if ( bidList == null ) {
            return null;
        }

        BidListDTO bidListDTO = new BidListDTO();

        bidListDTO.setId( bidList.getId() );
        bidListDTO.setAccount( bidList.getAccount() );
        bidListDTO.setType( bidList.getType() );
        bidListDTO.setBidQuantity( bidList.getBidQuantity() );

        return bidListDTO;
    }

    @Override
    public BidList BidListDTOToBidList(BidListDTO bidListDTO) {
        if ( bidListDTO == null ) {
            return null;
        }

        BidList bidList = new BidList();

        bidList.setId( bidListDTO.getId() );
        bidList.setAccount( bidListDTO.getAccount() );
        bidList.setType( bidListDTO.getType() );
        bidList.setBidQuantity( bidListDTO.getBidQuantity() );

        return bidList;
    }
}
