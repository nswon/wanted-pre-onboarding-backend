package com.wanted.wanted.application.application;

import com.wanted.wanted.application.domain.ApplicationHistory;
import com.wanted.wanted.application.domain.ApplicationHistoryRepository;
import com.wanted.wanted.application.dto.request.ApplicationApplyRequest;
import com.wanted.wanted.jobPosting.domain.JobPosting;
import com.wanted.wanted.jobPosting.domain.JobPostingRepository;
import com.wanted.wanted.user.domain.User;
import com.wanted.wanted.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationHistoryRepository applicationHistoryRepository;
    private final UserRepository userRepository;
    private final JobPostingRepository jobPostingRepository;

    public void apply(ApplicationApplyRequest applicationApplyRequest) {
        JobPosting jobPosting = jobPostingRepository.getById(applicationApplyRequest.jobPostingId());
        User user = userRepository.get();

        ApplicationHistory application = ApplicationHistory.create(jobPosting, user);

        jobPosting.receiveApplication(application);
        user.apply(application);

        applicationHistoryRepository.save(application);
    }
}
