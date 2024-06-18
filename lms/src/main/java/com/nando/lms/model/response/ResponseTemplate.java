package com.nando.lms.model.response;

import lombok.Data;

@Data
public abstract class ResponseTemplate {
    private int code;
    private String status;
    private String message;
}
