package com.spring.marketplace.service.impl;

import com.spring.marketplace.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    private static final String INN = "846851400117";

    @Override
    public List<String> getUsersInns(List<String> emails) {
        log.info("Call method getUsersEmails from mail-service");
        List<String> inns = new ArrayList<>();

        emails.forEach((element) -> inns.add(INN));

        return inns;
    }
}
