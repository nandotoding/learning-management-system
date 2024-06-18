package com.nando.lms.model.request;

import lombok.Data;

@Data
public class StudentRequest {
    private String username;
    private String password;
    private String studentName;
}
