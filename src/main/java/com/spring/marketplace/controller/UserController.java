package com.spring.marketplace.controller;

import com.spring.marketplace.dto.GetUserResponse;
import com.spring.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<GetUserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
