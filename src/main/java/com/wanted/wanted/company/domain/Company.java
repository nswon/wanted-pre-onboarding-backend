package com.wanted.wanted.company.domain;

import com.wanted.wanted.jobPosting.domain.JobPosting;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nation;

    @Column(nullable = false)
    private String area;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private final List<JobPosting> jobPostings = new ArrayList<>();

    @Builder
    public Company(String name, String nation, String area) {
        this.name = name;
        this.nation = nation;
        this.area = area;
    }

    public void createJobPosting(JobPosting jobPosting) {
        jobPostings.add(jobPosting);
    }

    public List<Long> getOtherJobPostings(JobPosting jobPosting) {
        return jobPostings.stream()
            .filter(posting -> posting.getId().equals(jobPosting.getId()))
            .map(JobPosting::getId)
            .toList();
    }
}
