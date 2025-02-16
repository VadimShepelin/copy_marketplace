package com.spring.marketplace.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "rateClient", url = "${app.feign-client.rate-service.url}")
@Component
public interface RateServiceClient {

    @GetMapping(value = "/rate")
    String getRateValue(@RequestHeader(name = "rate",required = false)String rate);
}
