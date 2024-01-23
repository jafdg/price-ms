package com.jafernandezg.price;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AppTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test1() throws Exception {

        URI uri = new URI("/prices?date=2020-12-14%2010:00:00&productId=35455&brandId=1");

        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15 16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31 23:59:59"))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void test2() throws Exception {

        URI uri = new URI("/prices?date=2020-12-14%2016:00:00&productId=35455&brandId=1");

        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15 16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31 23:59:59"))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void test3() throws Exception {

        URI uri = new URI("/prices?date=2020-12-14%2021:00:00&productId=35455&brandId=1");

        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15 16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31 23:59:59"))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void test4() throws Exception {

        URI uri = new URI("/prices?date=2020-12-15%2010:00:00&productId=35455&brandId=1");

        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15 16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31 23:59:59"))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void test5() throws Exception {

        URI uri = new URI("/prices?date=2020-12-16%2021:00:00&productId=35455&brandId=1");

        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.startDate").value("2020-06-15 16:00:00"))
                .andExpect(jsonPath("$.endDate").value("2020-12-31 23:59:59"))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.priority").value(1))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void gets_returnException_whenSendEmptyArgument() throws Exception {

        URI uri = new URI("/prices?date=2020-12-16%2021:00:00&productId=35455");

        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void gets_returnException_whenSendBadArgument() throws Exception {

        URI uri = new URI("/prices?date=2020-12-16%2021:00:00&productId=35455&brandId=1ñ");

        this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

}
