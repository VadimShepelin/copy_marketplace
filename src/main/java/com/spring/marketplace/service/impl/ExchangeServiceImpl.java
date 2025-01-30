package com.spring.marketplace.service.impl;

import com.spring.marketplace.client.RateServiceClient;
import com.spring.marketplace.exception.ApplicationException;
import com.spring.marketplace.service.ExchangeService;
import com.spring.marketplace.utils.enums.ErrorType;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.io.FileReader;
import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService<BigDecimal, BigDecimal> {

    @Value("${app.json.path}")
    private String pathToJson;
    @Value("${spring.redis.time-to-live}")
    private long redisTtl;
    @Value("${app.default.rate-value}")
    private String defaultRateValue;
    private final RateServiceClient rateServiceClient;
    private final JedisPool jedisPool;
    private final HttpSession session;

    @Override
    public BigDecimal convertCurrency(BigDecimal from, String rate) {
        try (FileReader fileReader = new FileReader(pathToJson)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
            String exchangeRate = jsonObject.get("exchangeRate" + rate) != null ?
                    ((String) jsonObject.get("exchangeRate" + rate)) :
                    session.getAttribute("rate") != null ?
                            String.valueOf(session.getAttribute("rate")) :
                            defaultRateValue;
            session.setAttribute("rate", exchangeRate);

            log.info("Successfully converted currency");
            return from.divide(new BigDecimal(exchangeRate), 2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new ApplicationException(ErrorType.FAILED_TO_CONVERT_CURRENCY);
        }
    }

    @Override
    public BigDecimal convertCurrencyWithCache(BigDecimal object, String rate) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (jedis.exists("exchangeRate")) {
                log.info("Get exchange rate from cache");
                return object.divide(new BigDecimal(jedis.get("exchangeRate")), 2, BigDecimal.ROUND_HALF_UP);
            }

            String exchangeRate = rateServiceClient.getRateValue(rate);
            exchangeRate = !exchangeRate.equals("noRate")?
                    exchangeRate : session.getAttribute("rate") != null ?
                    session.getAttribute("rate").toString() : defaultRateValue;

            session.setAttribute("rate", exchangeRate);
            log.info("call rate service getRateValue() method");
            jedis.setex("exchangeRate", redisTtl, exchangeRate);

            return object.divide(new BigDecimal(exchangeRate), 2, BigDecimal.ROUND_HALF_UP);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return convertCurrency(object, rate);
        }

    }

}