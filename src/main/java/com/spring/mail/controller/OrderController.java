package com.spring.mail.controller;

import com.spring.mail.dto.CreateOrderDto;
import com.spring.mail.dto.GetOrderResponse;
import com.spring.mail.dto.OrderWithProductsResponse;
import com.spring.mail.dto.UpdateOrderStateDto;
import com.spring.mail.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public GetOrderResponse createOrder(@NotNull @RequestParam("user_id") UUID userId, @Valid @RequestBody CreateOrderDto dto) {
        return orderService.createOrder(dto,userId);
    }

    @PutMapping("/state")
    public GetOrderResponse changeOrderState(@NotNull @RequestParam("order_id") UUID orderId, @Valid @RequestBody UpdateOrderStateDto dto){
        return orderService.updateOrderState(dto,orderId);
    }

    @PutMapping
    public ResponseEntity<String> addProductsToOrder(@NotNull @RequestParam("order_id") UUID orderId, @Valid @RequestBody CreateOrderDto dto){
        orderService.updateOrderProducts(dto,orderId);

        return ResponseEntity.ok("Add new products to order");
    }

    @GetMapping
    public List<OrderWithProductsResponse> getOrdersWithProducts() {
        return orderService.getOrdersWithProducts();
    }
}
