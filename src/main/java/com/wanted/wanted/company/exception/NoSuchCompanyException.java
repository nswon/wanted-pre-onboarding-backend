package com.wanted.wanted.company.exception;

public class NoSuchCompanyException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 회사입니다.";

    public NoSuchCompanyException() {
        super(MESSAGE);
    }
}
