package com.spring.marketplace.service;


import com.spring.marketplace.dto.CreateOrderDto;
import com.spring.marketplace.dto.GetOrderResponse;
import com.spring.marketplace.dto.OrderWithProductsResponse;
import com.spring.marketplace.dto.UpdateOrderStateDto;
import com.spring.marketplace.model.enums.Status;
import com.spring.marketplace.events.EventSource;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    GetOrderResponse createOrder(CreateOrderDto dto, UUID id);

    Status validateOrder(CreateOrderDto dto);

    void handleOrderEvent(EventSource eventSource);

    GetOrderResponse updateOrderState(UpdateOrderStateDto dto, UUID id);

    GetOrderResponse getOrderById(UUID id);

    void updateOrderProducts(CreateOrderDto dto, UUID id);

    List<OrderWithProductsResponse> getOrdersWithProducts();
}
