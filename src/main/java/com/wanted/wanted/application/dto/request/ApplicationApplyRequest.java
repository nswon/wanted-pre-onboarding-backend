package com.wanted.wanted.application.dto.request;

public record ApplicationApplyRequest(
    Long jobPostingId,
    Long userId
) {
}
