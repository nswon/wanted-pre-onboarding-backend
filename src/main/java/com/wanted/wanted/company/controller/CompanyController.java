package com.wanted.wanted.company.controller;

import com.wanted.wanted.company.application.CompanyService;
import com.wanted.wanted.company.dto.request.CompanyCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CompanyCreateRequest companyCreateRequest) {
        companyService.createCompany(companyCreateRequest);
        return ResponseEntity.noContent().build();
    }
}
