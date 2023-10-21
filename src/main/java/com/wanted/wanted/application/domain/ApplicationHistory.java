package com.wanted.wanted.application.domain;

import com.wanted.wanted.jobPosting.domain.JobPosting;
import com.wanted.wanted.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getJobPostingId() {
        return jobPosting.getId();
    }

    public Long getUserId() {
        return user.getId();
    }

    private ApplicationHistory(JobPosting jobPosting, User user) {
        this.jobPosting = jobPosting;
        this.user = user;
    }

    public static ApplicationHistory create(JobPosting jobPosting, User user) {
        return new ApplicationHistory(jobPosting, user);
    }
}
