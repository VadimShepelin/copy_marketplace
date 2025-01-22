package com.spring.marketplace.service.impl;

import com.spring.marketplace.exception.ApplicationException;
import com.spring.marketplace.service.ExchangeService;
import com.spring.marketplace.service.RateService;
import com.spring.marketplace.utils.enums.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.io.FileReader;
import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService<BigDecimal,BigDecimal> {

    @Value("${app.json.path}")
    private String pathToJson;
    private final RateService rateService;

    @Override
    @SneakyThrows
    public BigDecimal convertCurrency(BigDecimal from) {
        try(FileReader fileReader = new FileReader(pathToJson)){
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject)parser.parse(fileReader);
            String exchangeRate = (String)jsonObject.get("exchangeRate");

            log.info("Successfully converted currency");
            return from.divide(new BigDecimal(exchangeRate),2,BigDecimal.ROUND_HALF_UP);
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            throw new ApplicationException(ErrorType.FAILED_TO_CONVERT_CURRENCY);
        }
    }

    @Override
    @SneakyThrows
    public BigDecimal convertCurrencyWithCache(BigDecimal object) {
        try(Jedis jedis = new Jedis("localhost",6379)){
            BigDecimal exchangeRate = jedis.exists("exchangeRate")?
                    new BigDecimal(jedis.get("exchangeRate")):
                    new BigDecimal(rateService.getExchangeRate());

            log.info("convertCurrencyWithCache worked successfully");
            return object.divide(exchangeRate, 2, BigDecimal.ROUND_HALF_UP);
        }
        catch (Exception exception){
            log.error(exception.getMessage());
            return convertCurrency(object);
        }
    }


}
