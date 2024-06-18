package com.nando.lms.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
public class NotFoundException extends RuntimeException {
    private int code = 404;
    private String status = "NOT FOUND";

    public NotFoundException() {
        super("Not found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
