package com.wanted.wanted.jobPosting.application;

import com.wanted.wanted.company.domain.Company;
import com.wanted.wanted.company.domain.CompanyRepository;
import com.wanted.wanted.jobPosting.domain.JobPosting;
import com.wanted.wanted.jobPosting.domain.JobPostingRepository;
import com.wanted.wanted.jobPosting.dto.request.JobPostingCreateRequest;
import com.wanted.wanted.jobPosting.dto.request.JobPostingUpdateRequest;
import com.wanted.wanted.jobPosting.dto.response.JobPostingDetailResponse;
import com.wanted.wanted.jobPosting.dto.response.JobPostingResponse;
import com.wanted.wanted.jobPosting.dto.response.JobPostingsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class JobPostingService {
    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    public void createPosting(JobPostingCreateRequest request) {
        Company company = companyRepository.getById(request.companyId());
        JobPosting jobPosting = request.toEntity(company);
        company.createJobPosting(jobPosting);

        jobPostingRepository.save(jobPosting);
    }

    public JobPostingsResponse getPostings() {
        return jobPostingRepository.findAll().stream()
            .map(JobPostingResponse::new)
            .collect(collectingAndThen(toList(), JobPostingsResponse::new));
    }

    public JobPostingDetailResponse getPosting(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.getById(jobPostingId);
        return new JobPostingDetailResponse(jobPosting);
    }

    public void updatePosting(Long jobPostingId, JobPostingUpdateRequest request) {
        JobPosting jobPosting = jobPostingRepository.getById(jobPostingId);
        jobPosting.update(request.position(), request.compensation(), request.content(), request.technology());
    }

    public void deletePosting(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.getById(jobPostingId);
        jobPostingRepository.delete(jobPosting);
    }
}
