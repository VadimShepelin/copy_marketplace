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
import java.util.Optional;
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
        List<User> users = userRepository.findAllUsers();

        return Optional.of(users)
                .filter((item) -> !item.isEmpty())
                .flatMap((item) -> Optional.of(item.stream()
                        .map((element) -> conversionService.convert(element, GetUserResponse.class))
                        .toList()))
                .orElseThrow(() -> {
                    log.error("No Users found");
                    return new ApplicationException(ErrorType.NO_USERS_FOUND);
                });
    }
}
