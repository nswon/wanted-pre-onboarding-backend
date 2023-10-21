package com.wanted.wanted.company.domain;

import com.wanted.wanted.company.exception.NoSuchCompanyException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    default Company getById(Long id) {
        return findById(id)
            .orElseThrow(NoSuchCompanyException::new);
    }
}
