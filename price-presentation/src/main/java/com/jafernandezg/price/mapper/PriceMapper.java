package com.jafernandezg.price.mapper;

import com.jafernandezg.price.dto.PriceDTO;
import com.jafernandezg.price.vo.PriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Named("PriceMapper")
@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface PriceMapper {

    @Mappings({
            @Mapping(source = "startDate", target = "startDate", qualifiedByName = {"DateMapper", "convertLocalDateTimeToString"}),
            @Mapping(source = "endDate", target = "endDate", qualifiedByName = {"DateMapper", "convertLocalDateTimeToString"})
    })
    PriceDTO mapPriceVOToPriceDTO(PriceVO priceVO);

}
