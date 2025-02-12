package com.spring.mail.dto;

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
public class GetUserResponse {

    private String firstName;

    private String lastName;

    private List<GetOrderResponse> orders;
}
