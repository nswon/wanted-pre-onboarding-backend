package com.wanted.wanted.jobPosting.dto.request;

import com.wanted.wanted.company.domain.Company;
import com.wanted.wanted.jobPosting.domain.JobPosting;

public record JobPostingCreateRequest(
    Long companyId,
    String position,
    int compensation,
    String content,
    String technology
) {
    public JobPosting toEntity(Company company) {
        return JobPosting.builder()
            .position(position)
            .compensation(compensation)
            .content(content)
            .technology(technology)
            .company(company)
            .build();
    }
}
