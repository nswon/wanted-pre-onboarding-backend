package com.wanted.wanted.user.controller;

import com.wanted.wanted.application.dto.response.ApplicationHistoriesResponse;
import com.wanted.wanted.user.application.UserService;
import com.wanted.wanted.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        UserResponse userResponse = userService.getUser();
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/application-history")
    public ResponseEntity<ApplicationHistoriesResponse> getApplicationHistories() {
        ApplicationHistoriesResponse applicationHistoriesResponse = userService.getApplicationHistories();
        return ResponseEntity.ok(applicationHistoriesResponse);
    }
}
