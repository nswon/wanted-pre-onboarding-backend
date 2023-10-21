package com.wanted.wanted.application.exception;

import com.wanted.wanted.common.exception.BadRequestException;

public class AlreadyAppliedException extends BadRequestException {
    private static final String MESSAGE = "지원은 1번만 가능합니다.";

    public AlreadyAppliedException() {
        super(MESSAGE);
    }
}
