package com.spring.event.controller;

import com.spring.event.service.ProducerService;
import com.spring.source.events.EventSource;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping
    public ResponseEntity<String> sentEvent(@Valid @RequestBody EventSource eventSource){
        producerService.sentEvent(eventSource);
        return ResponseEntity.ok("Sent event successfully");
    }
}
