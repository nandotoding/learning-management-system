package com.nando.lms.service.implementation;

import com.nando.lms.exception.NotFoundException;
import com.nando.lms.exception.UnauthorizedException;
import com.nando.lms.model.request.StudentCredentialRequest;
import com.nando.lms.model.request.StudentRequest;
import com.nando.lms.model.request.TokenRequest;
import com.nando.lms.model.response.data.StudentResponseData;
import com.nando.lms.model.response.data.TokenResponseData;
import com.nando.lms.service.AuthorizationService;
import com.nando.lms.service.StudentService;
import com.nando.lms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImplementation implements AuthorizationService {
    private final StudentService studentService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthorizationServiceImplementation(StudentService studentService, JwtUtil jwtUtil) {
        this.studentService = studentService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public StudentResponseData registerStudent(StudentRequest studentRequest) {
        return studentService.addStudent(studentRequest);
    }

    @Override
    public TokenResponseData loginStudent(StudentCredentialRequest studentCredentialRequest) {
        try {
            StudentResponseData studentResponseData = studentService.getStudentByUsername(studentCredentialRequest.getUsername());
            String token = jwtUtil.generateToken("mysubject");
            jwtUtil.addTokenToStorage(token);
            return new TokenResponseData(studentResponseData.getId(), token);
        } catch (NotFoundException e) {
            throw new UnauthorizedException("Invalid username or password");
        }
    }

    @Override
    public boolean logoutStudent(TokenRequest tokenRequest) {
        if (jwtUtil.removeTokenFromStorage(tokenRequest.getToken())) {
            jwtUtil.addRevokedToken(tokenRequest.getToken());
            return true;
        } else {
            throw new UnauthorizedException("Token invalid");
        }
    }
}
