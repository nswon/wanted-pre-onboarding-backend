package com.wanted.wanted.jobPosting.dto.response;

import com.wanted.wanted.jobPosting.domain.JobPosting;

import java.util.List;

public record JobPostingDetailResponse(
    Long id,
    String companyName,
    String nation,
    String area,
    String position,
    int compensation,
    String technology,
    String content,
    List<Long> otherJobPostings
) {
    public JobPostingDetailResponse(JobPosting jobPosting) {
        this(
            jobPosting.getId(),
            jobPosting.getCompanyName(),
            jobPosting.getCompanyNation(),
            jobPosting.getCompanyArea(),
            jobPosting.getPosition(),
            jobPosting.getCompensation(),
            jobPosting.getTechnology(),
            jobPosting.getContent(),
            jobPosting.getOtherPostings()
        );
    }
}
