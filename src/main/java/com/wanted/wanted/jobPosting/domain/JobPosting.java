package com.wanted.wanted.jobPosting.domain;

import com.wanted.wanted.application.domain.ApplicationHistory;
import com.wanted.wanted.application.exception.AlreadyAppliedException;
import com.wanted.wanted.company.domain.Company;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;
    private int compensation;
    private String content;
    private String technology;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jobPosting")
    private final List<ApplicationHistory> applicationHistories = new ArrayList<>();

    @Builder
    public JobPosting(String position, int compensation, String content, String technology, Company company) {
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.technology = technology;
        this.company = company;
    }

    public void update(String position, int compensation, String content, String technology) {
        this.position = position;
        this.compensation = compensation;
        this.content = content;
        this.technology = technology;
    }

    public String getCompanyName() {
        return company.getName();
    }

    public String getCompanyNation() {
        return company.getNation();
    }

    public String getCompanyArea() {
        return company.getArea();
    }

    public List<Long> getOtherPostings() {
        return company.getOtherJobPostings(this);
    }

    public void receiveApplication(ApplicationHistory application) {
        if(isAlreadyApplied(application)) {
            throw new AlreadyAppliedException();
        }

        applicationHistories.add(application);
    }

    private boolean isAlreadyApplied(ApplicationHistory target) {
        return applicationHistories.stream().anyMatch(application -> application.getId().equals(target.getId()));
    }

    public List<Long> getApplicants() {
        return applicationHistories.stream()
            .map(ApplicationHistory::getUserId)
            .toList();
    }
}
