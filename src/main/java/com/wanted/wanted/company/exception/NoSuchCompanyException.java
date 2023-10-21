package com.wanted.wanted.company.exception;

import com.wanted.wanted.common.exception.NotFoundException;

public class NoSuchCompanyException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 회사입니다.";

    public NoSuchCompanyException() {
        super(MESSAGE);
    }
}
