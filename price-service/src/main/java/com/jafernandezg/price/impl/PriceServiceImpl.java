package com.jafernandezg.price.impl;

import com.jafernandezg.price.PriceService;
import com.jafernandezg.price.dao.PriceDao;
import com.jafernandezg.price.entity.Price;
import com.jafernandezg.price.exception.BadRequestException;
import com.jafernandezg.price.exception.NotFoundException;
import com.jafernandezg.price.mapper.PriceMapperVO;
import com.jafernandezg.price.vo.PriceVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private static final Logger LOG = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceDao priceDao;

    private final PriceMapperVO priceMapperVO;

    @Override
    @Transactional(readOnly = true)
    public PriceVO calculatePrices(LocalDateTime date, Long productId, Long brandId) {

        LOG.info("[calculatePrices] with params: date = {}, productId = {} and brandId = {}", date, productId, brandId);

        if (date == null) {
            LOG.error("The date is mandatory.");
            throw new BadRequestException("The date is mandatory.");
        }

        if (productId == null) {
            LOG.error("The productId is mandatory.");
            throw new BadRequestException("The productId is mandatory.");
        }

        if (brandId == null) {
            LOG.error("The brandId is mandatory.");
            throw new BadRequestException("The brandId is mandatory.");
        }

        Optional<Price> priceOptional = this.priceDao.findPriceByDateProductIdAndBrandId(date, productId, brandId);

        PriceVO priceVO;
        if (priceOptional.isPresent()) {
            priceVO = this.priceMapperVO.mapPriceToPriceVO(priceOptional.get());
        } else {
            LOG.error("The price was not found.");
            throw new NotFoundException("The price was not found.");
        }

        LOG.info("[calculatePrices] OK");

        return priceVO;
    }

}
