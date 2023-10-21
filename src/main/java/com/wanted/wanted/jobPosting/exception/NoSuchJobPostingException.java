package com.wanted.wanted.jobPosting.exception;

public class NoSuchJobPostingException extends RuntimeException {
    private static final String MESSAGE = "존재하지 않는 채용공고입니다.";

    public NoSuchJobPostingException() {
        super(MESSAGE);
    }
}
