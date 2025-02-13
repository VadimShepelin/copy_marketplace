package com.spring.marketplace.utils.converter;

import com.spring.marketplace.dto.GetOrderResponse;
import com.spring.marketplace.dto.GetUserResponse;
import com.spring.marketplace.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToGetUserResponseConverter implements Converter<User, GetUserResponse> {

    @Override
    public GetUserResponse convert(User source) {
        return GetUserResponse.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .orders(source.getOrders().stream().
                        map((item) ->
                                GetOrderResponse.builder()
                                        .status(item.getStatus())
                                        .totalCost(item.getTotalCost())
                                        .userId(item.getUser().getId())
                                        .build()).toList()
                ).build();
    }
}
