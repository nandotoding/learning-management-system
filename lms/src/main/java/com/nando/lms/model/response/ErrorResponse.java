package com.nando.lms.model.response;

import lombok.Data;

@Data
public class ErrorResponse extends ResponseTemplate {
    public ErrorResponse(int code, String status, String message) {
        super.setCode(code);
        super.setStatus(status);
        super.setMessage(message);
    }
}
