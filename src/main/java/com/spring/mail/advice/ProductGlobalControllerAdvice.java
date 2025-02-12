package com.spring.mail.advice;

import com.spring.mail.dto.GetProductResponse;
import com.spring.mail.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ProductGlobalControllerAdvice implements ResponseBodyAdvice<GetProductResponse> {

    private final ExchangeService<BigDecimal,BigDecimal> exchangeService;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return GetProductResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public GetProductResponse beforeBodyWrite(GetProductResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Optional.of(body).ifPresentOrElse((item) -> {
            String rate = String.join("", request.getHeaders().get("rate")!=null?request.getHeaders().get("rate").get(0):"");
            body.setPrice(exchangeService.convertCurrencyWithCache(body.getPrice(),rate));
            log.info("ProductGlobalControllerAdvice worked successfully");
        }, () -> {
            log.error("ProductGlobalControllerAdvice failed");
            throw new NullPointerException("GetProductResponse body return null");
        });

        return body;
    }
}
