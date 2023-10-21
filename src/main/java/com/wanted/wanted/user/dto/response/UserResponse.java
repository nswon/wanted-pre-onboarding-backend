package com.wanted.wanted.user.dto.response;

import com.wanted.wanted.user.domain.User;

public record UserResponse(
    Long id
) {
    public UserResponse(User user) {
        this(user.getId());
    }
}
