package com.jafernandezg.price;

import com.jafernandezg.price.dao.PriceDao;
import com.jafernandezg.price.entity.Price;
import com.jafernandezg.price.exception.BadRequestException;
import com.jafernandezg.price.exception.NotFoundException;
import com.jafernandezg.price.impl.PriceServiceImpl;
import com.jafernandezg.price.mapper.PriceMapperVO;
import com.jafernandezg.price.vo.PriceVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PriceServiceTests {

    private PriceDao priceDaoMock;
    private PriceMapperVO priceMapperVOMock;

    private PriceService priceService;

    @BeforeEach
    void setUp() {
        this.priceDaoMock = mock(PriceDao.class);
        this.priceMapperVOMock = mock(PriceMapperVO.class);

        this.priceService = new PriceServiceImpl(this.priceDaoMock, this.priceMapperVOMock);
    }

    @Test
    public void calculatePrices_returnException_whenDateIsNull() {

        // given
        // when
        // then
        Assertions.assertThrows(BadRequestException.class, () -> this.priceService.calculatePrices(null, 1L, 1L));
    }

    @Test
    public void calculatePrices_returnException_whenProductIdIsNull() {

        // given
        // when
        // then
        Assertions.assertThrows(BadRequestException.class, () -> this.priceService.calculatePrices(LocalDateTime.now(), null, 1L));
    }

    @Test
    public void calculatePrices_returnException_whenBrandIdIsNull() {

        // given
        // when
        // then
        Assertions.assertThrows(BadRequestException.class, () -> this.priceService.calculatePrices(LocalDateTime.now(), 1L, null));
    }

    @Test
    public void calculatePrices_returnPriceVO_whenAllIsOk() {

        // given
        final long productId = 1L;
        final long brandId = 1L;

        final Price price = new Price();
        price.setId(1L);
        price.setBrandId(brandId);
        price.setStartDate(LocalDateTime.MIN);
        price.setEndDate(LocalDateTime.MAX);
        price.setPriceList(1);
        price.setProductId(productId);
        price.setPriority(0);
        price.setPrice(2.5);
        price.setCurrency("EUR");

        final PriceVO priceVO = new PriceVO();
        priceVO.setId(1L);
        priceVO.setBrandId(brandId);
        priceVO.setStartDate(LocalDateTime.MIN);
        priceVO.setEndDate(LocalDateTime.MAX);
        priceVO.setPriceList(1);
        priceVO.setProductId(productId);
        priceVO.setPriority(0);
        priceVO.setPrice(2.5);
        priceVO.setCurrency("EUR");

        when(this.priceDaoMock.findPriceByDateProductIdAndBrandId(any(LocalDateTime.class), anyLong(), anyLong())).thenReturn(Optional.of(price));
        when(this.priceMapperVOMock.mapPriceToPriceVO(any(Price.class))).thenReturn(priceVO);

        // when
        PriceVO result = this.priceService.calculatePrices(LocalDateTime.now(), productId, brandId);

        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(priceVO.getId(), result.getId());
        Assertions.assertEquals(priceVO.getBrandId(), result.getBrandId());
        Assertions.assertEquals(priceVO.getStartDate(), result.getStartDate());
        Assertions.assertEquals(priceVO.getEndDate(), result.getEndDate());
        Assertions.assertEquals(priceVO.getPriceList(), result.getPriceList());
        Assertions.assertEquals(priceVO.getProductId(), result.getProductId());
        Assertions.assertEquals(priceVO.getPriority(), result.getPriority());
        Assertions.assertEquals(priceVO.getPrice(), result.getPrice());
        Assertions.assertEquals(priceVO.getCurrency(), result.getCurrency());
    }

    @Test
    public void calculatePrices_returnException_whenAllIsOkButNotFoundPrice() {

        // given
        when(this.priceDaoMock.findPriceByDateProductIdAndBrandId(any(LocalDateTime.class), anyLong(), anyLong())).thenReturn(Optional.ofNullable(null));

        // when
        // then
        Assertions.assertThrows(NotFoundException.class, () -> this.priceService.calculatePrices(LocalDateTime.now(), 1L, 1L));
    }

}
