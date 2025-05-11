package com.spring.marketplace.dto;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Dto to get info about order with products")
public class OrderWithProductsResponse {

    @Schema(description = "Id of the order", example = "550e8400-e29b-41d4-a716-446655440123")
    private UUID orderId;

    @Schema(description = "Order client first name", example = "Vadim")
    private String clientFirstName;

    @Schema(description = "Order client last name", example = "Shepelin")
    private String clientLastName;

    @Schema(description = "Order client inn", example = "14685140011")
    private String clientInn;

    @Schema(description = "Order client email", example = "lox@bk.ru")
    private String clientEmail;

    @Schema(description = "Order products")
    private List<ProductSkuAndQuantityDto> products;
}
