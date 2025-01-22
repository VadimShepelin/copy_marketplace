package com.spring.marketplace.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.spring.marketplace.service.RateService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;
import java.io.FileReader;

@Service
@Slf4j
public class RateServiceImpl implements RateService {

    @Value("${app.json.path}")
    private String pathToJson;

    @SneakyThrows
    @Override
    public String getExchangeRate(){
        try(FileReader fileReader = new FileReader(pathToJson);
            Jedis jedis = new Jedis("localhost",6379)) {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
            String exchangeRate = (String) jsonObject.get("exchangeRate");
            jedis.set("exchangeRate", exchangeRate, SetParams.setParams().ex(60));

            log.info("Get exchange rate: {}", exchangeRate);
            return exchangeRate;
        }
        catch(Exception e) {
            log.info("getExchangeRate failed to parse JSON");
            throw new JsonParseException(e.getMessage());
        }

    }
}
