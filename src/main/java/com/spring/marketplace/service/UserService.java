package com.spring.marketplace.service;

import com.spring.marketplace.dto.GetUserResponse;
import com.spring.marketplace.model.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);

    List<GetUserResponse> getAllUsers();
}
