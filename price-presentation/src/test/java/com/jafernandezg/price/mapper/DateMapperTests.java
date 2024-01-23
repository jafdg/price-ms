package com.jafernandezg.price.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DateMapperTests {

    private DateMapper dateMapper = new DateMapperImpl();

    @Test
    public void convertLocalDateTimeToString_returnString_whenAllIsOk() {

        // given
        // when
        String result = this.dateMapper.convertLocalDateTimeToString(LocalDateTime.now());

        // when
        Assertions.assertNotNull(result);
    }

    @Test
    public void convertStringToLocalDateTime_returnLocalDateTime_whenAllIsOk() {

        // given
        // when
        LocalDateTime result = this.dateMapper.convertStringToLocalDateTime("2024-05-05 04:05:06");

        // when
        Assertions.assertNotNull(result);
    }

    @Test
    public void convertStringToLocalDateTime_returnException_whenAllIsOk() {

        // given
        // when
        // when
        Assertions.assertThrows(DateTimeException.class, () -> this.dateMapper.convertStringToLocalDateTime("someText"));
    }

}
