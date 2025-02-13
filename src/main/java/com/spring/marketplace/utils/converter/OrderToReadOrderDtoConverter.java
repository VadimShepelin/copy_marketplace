package com.spring.marketplace.utils.converter;

import com.spring.marketplace.dto.GetOrderResponse;
import com.spring.marketplace.model.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToReadOrderDtoConverter implements Converter<Order, GetOrderResponse> {

    @Override
    public GetOrderResponse convert(Order source) {
        return GetOrderResponse.builder()
                .userId(source.getUser().getId())
                .status(source.getStatus())
                .totalCost(source.getTotalCost())
                .build();
    }
}
