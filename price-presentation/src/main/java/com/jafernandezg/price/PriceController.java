package com.jafernandezg.price;

import com.jafernandezg.price.controllers.PricesApiDelegate;
import com.jafernandezg.price.dto.PriceDTO;
import com.jafernandezg.price.mapper.PriceMapper;
import com.jafernandezg.price.mapper.PriceMapperVO;
import com.jafernandezg.price.mapper.DateMapper;
import com.jafernandezg.price.vo.PriceVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApiDelegate {

    private final PriceService priceService;

    private final DateMapper dateMapper;
    private final PriceMapper priceMapper;

    @Override
    public ResponseEntity<PriceDTO> calculatePrices(String date, Long productId, Long brandId) {

        PriceVO priceVO = this.priceService.calculatePrices(this.dateMapper.convertStringToLocalDateTime(date), productId, brandId);

        PriceDTO priceDTO = this.priceMapper.mapPriceVOToPriceDTO(priceVO);

        return ResponseEntity.ok(priceDTO);
    }

}
