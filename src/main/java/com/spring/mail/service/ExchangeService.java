package com.spring.mail.service;

public interface ExchangeService<T,K>{

    K convertCurrency(T object,String rate);
    K convertCurrencyWithCache(T object, String rate);
}
