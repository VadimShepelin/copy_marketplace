package com.spring.marketplace.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto to get info about user")
public class GetUserResponse {

    @Schema(description = "User first name", example = "Vadim")
    private String firstName;

    @Schema(description = "User last name", example = "Shepelin")
    private String lastName;

    @Schema(description = "User orders")
    private List<GetOrderResponse> orders;
}
