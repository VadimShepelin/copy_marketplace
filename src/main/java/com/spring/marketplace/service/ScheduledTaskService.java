package com.spring.marketplace.service;

import com.spring.marketplace.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@ConditionalOnProperty(value = "app.scheduling.enabled", matchIfMissing = false)
@Slf4j
@RequiredArgsConstructor
public class ScheduledTaskService {

    private final ProductRepository repository;

    @SneakyThrows
    @Transactional
    @Scheduled(fixedDelayString = "${app.scheduling.period}")
    public void increasePrice(){
        repository.findAllProductsAndLock().forEach((product) ->{
            product.setPrice(product.getPrice().multiply(new BigDecimal("1.1")));
        });
        repository.flush();
        log.info("Increase price by scheduled task");
        Thread.sleep(30000);
    }
}
