package com.zerobase.fastlms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAccessException.class)
    public String handelMemberException(Model model, InvalidAccessException e) {
        log.error(e.getMessage());
        return "error/invalid_access";
    }
}
