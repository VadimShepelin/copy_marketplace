package com.spring.marketplace.service;

public interface ExchangeService<T,K>{

    K convertCurrency(T object);
}
