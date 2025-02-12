package com.spring.mail.service;


import com.spring.mail.dto.CreateOrderDto;
import com.spring.mail.dto.GetOrderResponse;
import com.spring.mail.dto.OrderWithProductsResponse;
import com.spring.mail.dto.UpdateOrderStateDto;
import com.spring.mail.model.enums.Status;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    GetOrderResponse createOrder(CreateOrderDto dto, UUID id);

    Status validateOrder(CreateOrderDto dto);

    GetOrderResponse updateOrderState(UpdateOrderStateDto dto, UUID id);

    GetOrderResponse getOrderById(UUID id);

    void updateOrderProducts(CreateOrderDto dto, UUID id);

    List<OrderWithProductsResponse> getOrdersWithProducts();
}
