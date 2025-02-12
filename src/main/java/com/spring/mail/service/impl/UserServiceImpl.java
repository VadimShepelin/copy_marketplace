package com.spring.mail.service.impl;

import com.spring.mail.dto.GetUserResponse;
import com.spring.mail.exception.ApplicationException;
import com.spring.mail.model.User;
import com.spring.mail.repository.UserRepository;
import com.spring.mail.service.UserService;
import com.spring.mail.utils.enums.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    @Override
    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() ->{
                    log.error("User with this id not found");
                    return new ApplicationException(ErrorType.NO_SUCH_USER);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAllUsers() {
        return userRepository.findAllUsers().stream()
                .map((item) -> conversionService.convert(item, GetUserResponse.class))
                .toList();
    }
}
