package com.spring.mail.controller;

import com.spring.mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<Map<String,String>> getUsersInns(@RequestParam(name = "emails", required = false) List<String> emails){
        return ResponseEntity.ok(emailService.getUsersInns(emails));
    }
}
