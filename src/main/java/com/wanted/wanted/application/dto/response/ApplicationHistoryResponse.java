package com.wanted.wanted.application.dto.response;

import com.wanted.wanted.application.domain.ApplicationHistory;

public record ApplicationHistoryResponse(
    Long jobPostingId,
    Long userId
) {
    public ApplicationHistoryResponse(ApplicationHistory applicationHistory) {
        this(applicationHistory.getJobPostingId(), applicationHistory.getUserId());
    }
}
