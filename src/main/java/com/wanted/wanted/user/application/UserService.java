package com.wanted.wanted.user.application;

import com.wanted.wanted.application.dto.response.ApplicationHistoriesResponse;
import com.wanted.wanted.application.dto.response.ApplicationHistoryResponse;
import com.wanted.wanted.user.domain.User;
import com.wanted.wanted.user.domain.UserRepository;
import com.wanted.wanted.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse getUser() {
        User user = userRepository.get();
        return new UserResponse(user);
    }

    public ApplicationHistoriesResponse getApplicationHistories() {
        User user = userRepository.get();
        return user.getApplicationHistories().stream()
            .map(ApplicationHistoryResponse::new)
            .collect(collectingAndThen(toList(), ApplicationHistoriesResponse::new));
    }
}
