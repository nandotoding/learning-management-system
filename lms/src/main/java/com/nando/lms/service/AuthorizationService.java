package com.nando.lms.service;

import com.nando.lms.model.request.StudentCredentialRequest;
import com.nando.lms.model.request.StudentRequest;
import com.nando.lms.model.request.TokenRequest;
import com.nando.lms.model.response.data.StudentResponseData;
import com.nando.lms.model.response.data.TokenResponseData;

public interface AuthorizationService {
    public StudentResponseData registerStudent(StudentRequest studentRequest);
    public TokenResponseData loginStudent(StudentCredentialRequest studentCredentialRequest);
    public boolean logoutStudent(TokenRequest tokenRequest);
//    public boolean isTokenRevoked(String token);
}
