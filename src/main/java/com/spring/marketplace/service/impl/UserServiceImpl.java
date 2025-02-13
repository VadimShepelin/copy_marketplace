package com.spring.marketplace.service.impl;

import com.spring.marketplace.dto.GetUserResponse;
import com.spring.marketplace.exception.ApplicationException;
import com.spring.marketplace.model.User;
import com.spring.marketplace.repository.UserRepository;
import com.spring.marketplace.service.UserService;
import com.spring.marketplace.utils.enums.ErrorType;
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
