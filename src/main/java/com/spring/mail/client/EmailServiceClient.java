package com.spring.mail.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;

@FeignClient(name = "emailClient", url = "${app.feign-client.mail-service.url}")
@Component
public interface EmailServiceClient {

    @GetMapping(value = "/email")
    List<String> getUsersInns(@RequestHeader(name = "emails",required = false) List<String> emails);
}
