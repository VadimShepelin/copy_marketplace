package com.spring.marketplace.service.impl;

import com.spring.marketplace.exception.ApplicationException;
import com.spring.marketplace.service.ExchangeService;
import com.spring.marketplace.utils.enums.ErrorType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.math.BigDecimal;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService<BigDecimal,BigDecimal> {

    @Value("${app.json.path}")
    private String pathToJson;

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
}
