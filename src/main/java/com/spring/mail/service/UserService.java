package com.spring.mail.service;

import com.spring.mail.dto.GetUserResponse;
import com.spring.mail.model.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);

    List<GetUserResponse> getAllUsers();
}
