package com.jafernandezg.price;

import com.jafernandezg.price.PriceController;
import com.jafernandezg.price.PriceService;
import com.jafernandezg.price.dto.PriceDTO;
import com.jafernandezg.price.mapper.DateMapper;
import com.jafernandezg.price.mapper.PriceMapper;
import com.jafernandezg.price.vo.PriceVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceControllerTests {

    private PriceService priceServiceMock;
    private DateMapper dateMapperMock;
    private PriceMapper priceMapperMock;

    private PriceController priceController;

    @BeforeEach
    void setUp() {
        this.priceServiceMock = mock(PriceService.class);
        this.dateMapperMock = mock(DateMapper.class);
        this.priceMapperMock = mock(PriceMapper.class);

        this.priceController = new PriceController(this.priceServiceMock, this.dateMapperMock, this.priceMapperMock);
    }

    @Test
    public void calculatePrices_returnPriceDTO_whenAllIsOk() {

        // given
        final long productId = 1L;
        final long brandId = 1L;

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

        final PriceDTO priceDTO = new PriceDTO();
        priceDTO.setBrandId(brandId);
        priceDTO.setStartDate("2023-10-31 23:59:59");
        priceDTO.setEndDate("2023-12-31 23:59:59");
        priceDTO.setPriceList(1);
        priceDTO.setProductId(productId);
        priceDTO.setPriority(0);
        priceDTO.setPrice(2.5);
        priceDTO.setCurrency("EUR");

        when(this.dateMapperMock.convertStringToLocalDateTime(anyString())).thenReturn(LocalDateTime.now());
        when(this.priceServiceMock.calculatePrices(any(LocalDateTime.class), anyLong(), anyLong())).thenReturn(priceVO);
        when(this.priceMapperMock.mapPriceVOToPriceDTO(any(PriceVO.class))).thenReturn(priceDTO);

        // when
        ResponseEntity<PriceDTO> responseEntity = this.priceController.calculatePrices("2020-11-31 23:59:59", productId, brandId);

        // then
        Assertions.assertNotNull(responseEntity);
        Assertions.assertNotNull(responseEntity.getStatusCode());
        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(200, responseEntity.getStatusCode().value());
        Assertions.assertNotNull(responseEntity.getBody());

        PriceDTO result = responseEntity.getBody();
        Assertions.assertEquals(priceDTO.getBrandId(), result.getBrandId());
        Assertions.assertEquals(priceDTO.getStartDate(), result.getStartDate());
        Assertions.assertEquals(priceDTO.getEndDate(), result.getEndDate());
        Assertions.assertEquals(priceDTO.getPriceList(), result.getPriceList());
        Assertions.assertEquals(priceDTO.getProductId(), result.getProductId());
        Assertions.assertEquals(priceDTO.getPriority(), result.getPriority());
        Assertions.assertEquals(priceDTO.getPrice(), result.getPrice());
        Assertions.assertEquals(priceDTO.getCurrency(), result.getCurrency());

    }

}
