package com.spring.marketplace.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Builder;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class OrderWithProductsResponse {

    private UUID orderId;

    private String clientFirstName;

    private String clientLastName;

    private String clientInn;

    private String clientEmail;

    private List<ProductSkuAndQuantityDto> products;
}
