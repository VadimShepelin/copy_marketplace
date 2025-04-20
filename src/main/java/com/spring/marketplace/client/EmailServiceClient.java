package com.spring.marketplace.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

@FeignClient(name = "email-service", url = "${app.feign-client.mail-service.url}")
@Component
public interface EmailServiceClient {

    @GetMapping(value = "/email")
    Map<String,String> getUsersInns(@RequestParam(name = "emails",required = false) List<String> emails);
}
