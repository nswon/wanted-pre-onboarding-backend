package com.wanted.wanted.jobPosting.exception;

import com.wanted.wanted.common.exception.NotFoundException;

public class NoSuchJobPostingException extends NotFoundException {
    private static final String MESSAGE = "존재하지 않는 채용공고입니다.";

    public NoSuchJobPostingException() {
        super(MESSAGE);
    }
}
