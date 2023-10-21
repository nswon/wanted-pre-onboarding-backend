package com.wanted.wanted.company.dto.request;

import com.wanted.wanted.company.domain.Company;

public record CompanyCreateRequest(
    String name,
    String nation,
    String area
) {
    public Company toEntity() {
        return Company.builder()
            .name(name)
            .nation(nation)
            .area(area)
            .build();
    }
}
