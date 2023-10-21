package com.wanted.wanted.jobPosting.domain;

import com.wanted.wanted.jobPosting.exception.NoSuchJobPostingException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {

    default JobPosting getById(Long id) {
        return findById(id)
            .orElseThrow(NoSuchJobPostingException::new);
    }
}
