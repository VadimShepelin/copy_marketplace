package com.spring.mail.service;

import java.util.List;
import java.util.Map;

public interface EmailService {
    Map<String,String> getUsersInns(List<String> email);
}
