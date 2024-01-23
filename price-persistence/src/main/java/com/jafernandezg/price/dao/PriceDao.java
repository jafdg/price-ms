package com.jafernandezg.price.dao;

import com.jafernandezg.price.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceDao extends JpaRepository<Price, Long> {

    @Query(value = """
            SELECT price.*
            FROM prices price
            WHERE price.product_id = :productId AND price.brand_id = :brandId AND :date BETWEEN price.start_date AND price.end_date
            ORDER BY price.priority DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<Price> findPriceByDateProductIdAndBrandId(LocalDateTime date, Long productId, Long brandId);

}
