package com.wanted.wanted.jobPosting.dto.response;

import com.wanted.wanted.jobPosting.domain.JobPosting;

public record JobPostingResponse(
    Long id,
    String companyName,
    String nation,
    String area,
    String position,
    int compensation,
    String technology
) {
    public JobPostingResponse(JobPosting jobPosting) {
        this(
            jobPosting.getId(),
            jobPosting.getCompanyName(),
            jobPosting.getCompanyNation(),
            jobPosting.getCompanyArea(),
            jobPosting.getPosition(),
            jobPosting.getCompensation(),
            jobPosting.getTechnology()
        );
    }
}
