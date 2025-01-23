package com.spring.marketplace.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "rateClient", url = "http://localhost:8080/")
@Component
public interface RateServiceClient {

    @GetMapping(value = "/rate")
    String getRateValue();
}
