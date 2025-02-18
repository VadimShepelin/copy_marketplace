package com.spring.event.service;


import com.spring.source.events.EventSource;

public interface ProducerService {
    void sentEvent(EventSource eventSource);
}
