package com.spring.mail.service.impl;

import com.spring.mail.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static long inn = 14685140011L;

    @Override
    public Map<String, String> getUsersInns(List<String> emails) {
        log.info("Call method getUsersEmails from mail-service");
        Map<String, String> result = new HashMap<>();

        emails.forEach((element) -> {
            result.put(element, String.valueOf(inn++));
        });

        return result;
    }
}
