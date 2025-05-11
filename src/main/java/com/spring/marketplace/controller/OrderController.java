package com.spring.marketplace.controller;

import com.spring.marketplace.docs.order.*;
import com.spring.marketplace.dto.CreateOrderDto;
import com.spring.marketplace.dto.GetOrderResponse;
import com.spring.marketplace.dto.OrderWithProductsResponse;
import com.spring.marketplace.dto.UpdateOrderStateDto;
import com.spring.marketplace.service.OrderService;
import com.spring.marketplace.events.EventSource;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Tag(name = "Order Controller", description = "Used to create new orders, update the" +
        "status of existing ones and etc")
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CreateOrderDoc
    @ResponseStatus(HttpStatus.CREATED)
    public GetOrderResponse createOrder(@NotNull @RequestParam("user_id") UUID userId, @Valid @RequestBody CreateOrderDto dto) {
        return orderService.createOrder(dto,userId);
    }

    @PutMapping("/state")
    @ChangeOrderStateDoc
    public GetOrderResponse changeOrderState(@NotNull @RequestParam("order_id") UUID orderId, @Valid @RequestBody UpdateOrderStateDto dto){
        return orderService.updateOrderState(dto,orderId);
    }

    @PutMapping
    @AddProductsToOrderDoc
    public ResponseEntity<String> addProductsToOrder(@NotNull @RequestParam("order_id") UUID orderId, @Valid @RequestBody CreateOrderDto dto){
        orderService.updateOrderProducts(dto,orderId);

        return ResponseEntity.ok("Add new products to order");
    }

    @GetMapping
    @CircuitBreaker(name = "getOrdersWithProducts", fallbackMethod = "getOrdersByStatus")
    @GetOrdersWithProductsDoc
    public List<OrderWithProductsResponse> getOrdersWithProducts(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "5") int pageSize) {
        return orderService.getOrdersWithProducts(pageNo,pageSize);
    }

    @PostMapping("/handle")
    @HandleOrderEventDoc
    public ResponseEntity<String> handleOrderEvent(@Valid @RequestBody EventSource eventSource) {
        orderService.handleOrderEvent(eventSource);

        return ResponseEntity.ok("Order event handled successfully");
    }

    @GetMapping("/fallback")
    @GetOrderByStatusDoc
    public List<GetOrderResponse> getOrdersByStatus(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "3") int pageSize, @Parameter(hidden = true) Exception ex){
        return orderService.getOrdersByStatus(pageNo,pageSize);
    }
}
