package com.wanted.wanted.application.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationHistoryRepository extends JpaRepository<ApplicationHistory, Long> {
}
