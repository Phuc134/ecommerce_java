package org.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.dto.request.UserRequest;
import org.example.ecommerce.dto.response.UserResponse;
import org.example.ecommerce.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    @PostMapping
    public UserResponse creatUser(@RequestBody UserRequest request) {
        return userService.create(request);
    }
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id) {
        return userService.getUser(id);
    }
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getUsers();
    }
}
