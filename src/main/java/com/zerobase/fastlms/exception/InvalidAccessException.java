package com.zerobase.fastlms.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidAccessException extends RuntimeException {
    public InvalidAccessException(String errorMessage) {
        super(errorMessage);
    }
}
