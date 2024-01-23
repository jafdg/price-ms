package com.jafernandezg.price;

import com.jafernandezg.price.vo.PriceVO;

import java.time.LocalDateTime;

public interface PriceService {

    PriceVO calculatePrices(LocalDateTime date, Long productId, Long brandId);

}
