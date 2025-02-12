package com.spring.mail.service.impl;

import com.spring.mail.client.EmailServiceClient;
import com.spring.mail.dto.*;
import com.spring.mail.exception.ApplicationException;
import com.spring.mail.model.Order;
import com.spring.mail.model.OrderItems;
import com.spring.mail.model.enums.Status;
import com.spring.mail.repository.OrderItemsRepository;
import com.spring.mail.repository.OrderRepository;
import com.spring.mail.service.OrderService;
import com.spring.mail.service.ProductService;
import com.spring.mail.service.UserService;
import com.spring.mail.utils.enums.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OderServiceImpl implements OrderService {

    private final ProductService productService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ConversionService conversionService;
    private final OrderItemsRepository orderItemsRepository;
    private final EmailServiceClient client;

    @Override
    @Transactional
    public GetOrderResponse createOrder(CreateOrderDto dto, UUID id) {
        Status orderStatus = validateOrder(dto);

        Order order = Order.builder()
                .status(orderStatus)
                .orderId(UUID.randomUUID())
                .user(userService.getUserById(id))
                .build();

        double orderTotalPrice = dto.getProductMap().entrySet().stream()
                .mapToDouble((item) -> {
                    orderItemsRepository.save(OrderItems.builder()
                            .sku(item.getKey())
                            .quantity(item.getValue())
                            .orderId(order.getOrderId())
                            .build());

                    GetProductResponse product = productService.getProductBySku(item.getKey());

                    return item.getValue().doubleValue() *
                            product.getPrice().doubleValue();
                }).sum();

        dto.getProductMap().forEach(productService::reduceProductQuantity);
        log.info("Update Quantity was successful");

        order.setTotalCost(BigDecimal.valueOf(orderTotalPrice));

        log.info("Save order: {}", order);
        return conversionService.convert(orderRepository.save(order), GetOrderResponse.class);
    }


    @Override
    @Transactional(readOnly = true)
    public Status validateOrder(CreateOrderDto dto) {
        dto.getProductMap().entrySet().stream().
                filter((item) -> productService.getProductBySku(item.getKey()).getQuantity().compareTo(item.getValue()) < 0)
                .findAny().ifPresent((element) -> {
                    log.info("Insufficient quantity of products");
                    throw new ApplicationException(ErrorType.INSUFFICIENT_QUANTITY_OF_PRODUCTS);
                });

        return Status.CREATED;
    }

    @Override
    @Transactional
    public GetOrderResponse updateOrderState(UpdateOrderStateDto dto, UUID id) {
        return orderRepository.findOrderById(id)
                .map((item) -> {
                    if (dto.getStatus() == Status.DONE && item.getStatus() == Status.CREATED) {
                        item.setStatus(Status.DONE);
                        log.info("Change order status to DONE");
                        return conversionService.convert(orderRepository.save(item), GetOrderResponse.class);
                    } else if (dto.getStatus() == Status.REJECTED && item.getStatus() == Status.CREATED) {
                        orderItemsRepository.findAllByOrderId(id)
                                .forEach((element) -> {
                                    productService.increaseProductQuantity(element.getSku(), element.getQuantity());
                                    orderItemsRepository.delete(element);
                                });

                        item.setStatus(Status.REJECTED);
                        log.info("Change order status to REJECTED");
                        return conversionService.convert(orderRepository.save(item), GetOrderResponse.class);
                    }

                    log.error("Failed to change order status");
                    throw new ApplicationException(ErrorType.FAILED_TO_CHANGE_ORDER_STATUS);
                }).orElseThrow(() -> {
                    log.error("No such order");
                    return new ApplicationException(ErrorType.NOT_SUCH_ORDER);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public GetOrderResponse getOrderById(UUID id) {
        return orderRepository.findById(id).map(
                (item) -> conversionService.convert(item, GetOrderResponse.class)
        ).orElseThrow(() -> {
            log.error("No such order");
            return new ApplicationException(ErrorType.NOT_SUCH_ORDER);
        });
    }

    @Override
    @Transactional
    public void updateOrderProducts(CreateOrderDto dto, UUID id) {
        validateOrder(dto);

        Order order = orderRepository.findOrderById(id).orElseThrow(() -> {
            log.error("No such order");
            return new ApplicationException(ErrorType.NOT_SUCH_ORDER);
        });

        if (order.getStatus() == Status.CREATED) {
            double totalProductPrice = dto.getProductMap().entrySet().stream()
                    .mapToDouble((item) -> {
                        orderItemsRepository.save(OrderItems.builder()
                                .sku(item.getKey())
                                .quantity(item.getValue())
                                .orderId(id)
                                .build());

                        return item.getValue().doubleValue() *
                                productService.getProductBySku(item.getKey()).getPrice().doubleValue();
                    }).sum();

            orderRepository.updateOrderTotalCost(id, totalProductPrice);
            dto.getProductMap().forEach(productService::reduceProductQuantity);
            log.info("Update Order successful");
        }
        else{
            log.error("Failed to update order");
            throw new ApplicationException(ErrorType.FAILED_TO_UPDATE_ORDER_PRODUCTS);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderWithProductsResponse> getOrdersWithProducts() {
        List<Order> allOrders = orderRepository.findAll();
        List<String> ordersEmail = allOrders
                .stream().map((item) -> item.getUser().getEmail())
                .toList();
        List<String> userInns = client.getUsersInns(ordersEmail);
        List<OrderWithProductsResponse> result = new ArrayList<>();

        allOrders.forEach((item) -> {
            List<ProductSkuAndQuantityDto> orderProducts = new ArrayList<>();
            orderItemsRepository.findAllByOrderId(item.getOrderId()).
                    forEach((element) -> {
                        orderProducts.add(ProductSkuAndQuantityDto.builder()
                                .sku(element.getSku())
                                .quantity(element.getQuantity())
                                .build());
                    });

            if(!orderProducts.isEmpty()) {
                OrderWithProductsResponse orderResponse = OrderWithProductsResponse.builder()
                        .orderId(item.getOrderId())
                        .products(orderProducts)
                        .clientInn(userInns.get(0))
                        .build();

                result.add(orderResponse);
            }
        });

        log.info("Get Orders successful");
        return result;
    }
}
