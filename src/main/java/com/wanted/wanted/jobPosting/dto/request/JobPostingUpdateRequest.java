package com.wanted.wanted.jobPosting.dto.request;

public record JobPostingUpdateRequest(
    String position,
    int compensation,
    String content,
    String technology
) {

}
