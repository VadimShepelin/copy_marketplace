package com.spring.marketplace.controller;

import com.spring.marketplace.docs.user.GetAllUsersDoc;
import com.spring.marketplace.dto.GetUserResponse;
import com.spring.marketplace.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "User Controller", description = "Used to get all users with where orders")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @GetAllUsersDoc
    public List<GetUserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
