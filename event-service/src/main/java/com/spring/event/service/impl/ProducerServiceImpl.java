package com.spring.event.service.impl;

import com.spring.event.service.ProducerService;
import com.spring.source.events.EventSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.Uuid;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final KafkaTemplate<String, EventSource> kafkaTemplate;

    @Override
    public void sentEvent(EventSource eventSource) {
        kafkaTemplate.send("events",String.valueOf(Uuid.randomUuid()), eventSource);
        log.info("Sent event: {}", eventSource);
    }
}
