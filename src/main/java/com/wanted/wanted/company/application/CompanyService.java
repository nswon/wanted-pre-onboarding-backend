package com.wanted.wanted.company.application;

import com.wanted.wanted.company.domain.Company;
import com.wanted.wanted.company.domain.CompanyRepository;
import com.wanted.wanted.company.dto.request.CompanyCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public void createCompany(CompanyCreateRequest request) {
        Company company = request.toEntity();
        companyRepository.save(company);
    }
}
