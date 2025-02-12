package com.spring.marketplace.controller;

import com.spring.marketplace.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<List<String>> getUsersInns(@RequestHeader(name = "emails", required = false) List<String> emails){
        return ResponseEntity.ok(emailService.getUsersInns(emails));
    }
}
