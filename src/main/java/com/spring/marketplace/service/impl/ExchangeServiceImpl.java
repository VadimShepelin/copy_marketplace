package com.spring.marketplace.service.impl;

import com.spring.marketplace.client.RateServiceClient;
import com.spring.marketplace.exception.ApplicationException;
import com.spring.marketplace.service.ExchangeService;
import com.spring.marketplace.utils.enums.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.io.FileReader;
import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService<BigDecimal,BigDecimal> {

    @Value("${app.json.path}")
    private String pathToJson;
    private final RateServiceClient rateServiceClient;
    private final JedisPool jedisPool;

    @Override
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
    @Cacheable("exchangeRate")
    public BigDecimal convertCurrencyWithCache(BigDecimal object) {
        try(Jedis jedis = jedisPool.getResource()){
            String exchangeRate = rateServiceClient.getRateValue();
            log.info("call rate service getRateValue() method");
            jedis.set("exchangeRate", exchangeRate);

            return object.divide(new BigDecimal(exchangeRate), 2, BigDecimal.ROUND_HALF_UP);
        }
        catch (Exception exception){
            log.error(exception.getMessage());
            return convertCurrency(object);
        }
    }


}
