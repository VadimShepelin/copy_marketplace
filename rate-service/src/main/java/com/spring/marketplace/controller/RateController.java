package com.spring.marketplace.controller;

import com.spring.marketplace.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rate")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @GetMapping
    public ResponseEntity<String> getRateValue(@RequestHeader(name = "rate", required = false) String rate){

        return ResponseEntity.ok().body(rateService.getRateValue(rate));
    }
}
