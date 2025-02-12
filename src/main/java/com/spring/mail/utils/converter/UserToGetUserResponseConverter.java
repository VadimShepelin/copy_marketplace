package com.spring.mail.utils.converter;

import com.spring.mail.dto.GetOrderResponse;
import com.spring.mail.dto.GetUserResponse;
import com.spring.mail.model.User;
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
