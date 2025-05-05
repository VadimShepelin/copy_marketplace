package com.spring.marketplace.repository;

import com.spring.marketplace.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, UUID> {

    @Query("SELECT i FROM OrderItems i where i.orderId = :orderId")
    List<OrderItems> findAllByOrderId(UUID orderId);
}
