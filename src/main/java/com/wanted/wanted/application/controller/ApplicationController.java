package com.wanted.wanted.application.controller;

import com.wanted.wanted.application.application.ApplicationService;
import com.wanted.wanted.application.dto.request.ApplicationApplyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<Void> apply(@RequestBody ApplicationApplyRequest applicationApplyRequest) {
        applicationService.apply(applicationApplyRequest);
        return ResponseEntity.noContent().build();
    }
}
