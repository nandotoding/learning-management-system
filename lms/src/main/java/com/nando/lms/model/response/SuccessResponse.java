package com.nando.lms.model.response;

import lombok.Data;

@Data
public class SuccessResponse<T> extends ResponseTemplate {
    private T data;
    private String message = "Success";

    public SuccessResponse(int code, String status, T data) {
        super.setCode(code);
        super.setStatus(status);
        super.setMessage(this.message);
        this.data = data;
    }

    public SuccessResponse(int code, String status, String message, T data) {
        super.setCode(code);
        super.setStatus(status);
        super.setMessage(message);
        this.data = data;
    }
}
