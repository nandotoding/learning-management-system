package com.nando.lms.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
public class BadRequestException extends RuntimeException {
    private int code = 400;
    private String status = "BAD REQUEST";

    public BadRequestException() {
        super("Bad request");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
