package com.wanted.wanted.jobPosting.controller;

import com.wanted.wanted.application.dto.response.ApplicationApplicantsResponse;
import com.wanted.wanted.jobPosting.application.JobPostingService;
import com.wanted.wanted.jobPosting.dto.request.JobPostingCreateRequest;
import com.wanted.wanted.jobPosting.dto.request.JobPostingUpdateRequest;
import com.wanted.wanted.jobPosting.dto.response.JobPostingDetailResponse;
import com.wanted.wanted.jobPosting.dto.response.JobPostingsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-posting")
@RequiredArgsConstructor
public class JobPostingController {
    private final JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody JobPostingCreateRequest jobPostingCreateRequest) {
        jobPostingService.createPosting(jobPostingCreateRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<JobPostingsResponse> getPostings() {
        JobPostingsResponse jobPostingsResponse = jobPostingService.getPostings();
        return ResponseEntity.ok(jobPostingsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDetailResponse> getPosting(@PathVariable("id") Long jobPostingId) {
        JobPostingDetailResponse jobPostingDetailResponse = jobPostingService.getPosting(jobPostingId);
        return ResponseEntity.ok(jobPostingDetailResponse);
    }

    @GetMapping("/{id}/applicants")
    public ResponseEntity<ApplicationApplicantsResponse> getApplicants(@PathVariable("id") Long jobPostingId) {
        ApplicationApplicantsResponse applicationApplicantsResponse = jobPostingService.getApplicants(jobPostingId);
        return ResponseEntity.ok(applicationApplicantsResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
        @PathVariable("id") Long jobPostingId,
        @RequestBody JobPostingUpdateRequest jobPostingUpdateRequest
    ) {
        jobPostingService.updatePosting(jobPostingId, jobPostingUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long jobPostingId) {
        jobPostingService.deletePosting(jobPostingId);
        return ResponseEntity.noContent().build();
    }
}
