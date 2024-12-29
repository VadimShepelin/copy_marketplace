package com.spring.marketplace.repository;

import com.spring.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    Optional<Product> findBySku(String sku);
    Optional<Product> findBySkuAndIdNot(String sku, UUID id);
}
