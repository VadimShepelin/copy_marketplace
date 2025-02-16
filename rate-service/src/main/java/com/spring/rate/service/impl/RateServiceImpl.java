package com.spring.rate.service.impl;

import com.spring.rate.service.RateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {


    @Override
    public String getRateValue(String rate) {
        log.info("Get rate value from the microservice: rate-service");
        if(rate!=null && rate.equals("USD")){
            return "105.32";
        }
        else if(rate!=null && rate.equals("EUR")){
            return "123.32";
        }
        else if(rate!=null && rate.equals("RUB")){
            return "1";
        }

        return "noRate";
    }
}
