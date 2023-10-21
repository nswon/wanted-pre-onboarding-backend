package com.wanted.wanted.jobPosting.dto.response;

import java.util.List;

public record JobPostingsResponse(
    List<JobPostingResponse> jobPostingResponses
) {

}
