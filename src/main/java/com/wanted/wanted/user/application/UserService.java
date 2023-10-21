package com.wanted.wanted.user.application;

import com.wanted.wanted.user.domain.User;
import com.wanted.wanted.user.domain.UserRepository;
import com.wanted.wanted.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUser() {
        User user = userRepository.get();
        return new UserResponse(user);
    }
}
