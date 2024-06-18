package com.nando.lms.model.request;

import lombok.Data;

@Data
public class StudentCredentialRequest {
    private String username;
    private String password;
}
