package com.jafernandezg.price.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

@Named("DateMapper")
@Mapper(componentModel = "spring")
public interface DateMapper {

    @Named("convertStringToLocalDateTime")
    default LocalDateTime convertStringToLocalDateTime(String date) {

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .toFormatter(new Locale("es", "ES"));

        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    @Named("convertLocalDateTimeToString")
    default String convertLocalDateTimeToString(LocalDateTime localDateTime) {

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .toFormatter(new Locale("es", "ES"));

        return localDateTime.format(dateTimeFormatter);
    }

}
