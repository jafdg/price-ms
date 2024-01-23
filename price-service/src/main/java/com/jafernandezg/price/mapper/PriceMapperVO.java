package com.jafernandezg.price.mapper;

import com.jafernandezg.price.entity.Price;
import com.jafernandezg.price.vo.PriceVO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Named("PriceMapperVO")
@Mapper(componentModel = "spring")
public interface PriceMapperVO {

    PriceVO mapPriceToPriceVO(Price price);

}
