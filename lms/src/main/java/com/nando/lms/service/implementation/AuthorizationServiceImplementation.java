package com.nando.lms.service.implementation;

import com.nando.lms.exception.BadRequestException;
import com.nando.lms.exception.NotFoundException;
import com.nando.lms.exception.UnauthorizedException;
import com.nando.lms.model.entity.Student;
import com.nando.lms.model.request.StudentCredentialRequest;
import com.nando.lms.model.request.StudentRequest;
import com.nando.lms.model.request.TokenRequest;
import com.nando.lms.model.response.data.StudentResponseData;
import com.nando.lms.model.response.data.TokenResponseData;
import com.nando.lms.repository.StudentRepository;
import com.nando.lms.service.AuthorizationService;
import com.nando.lms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationServiceImplementation implements AuthorizationService {
    private final StudentRepository studentRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthorizationServiceImplementation(StudentRepository studentRepository, JwtUtil jwtUtil) {
        this.studentRepository = studentRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public StudentResponseData registerStudent(StudentRequest studentRequest) {
        if (studentRequest == null) {
            throw new BadRequestException("Empty request body");
        }

        if (studentRepository.getStudentByUsername(studentRequest.getUsername()).orElse(null) != null) {
            throw new BadRequestException("Username already exist");
        }

        String id = UUID.randomUUID().toString();
        studentRepository.addStudent(id, studentRequest.getUsername(), studentRequest.getPassword(), studentRequest.getStudentName());
        return new StudentResponseData(id, studentRequest.getUsername(), studentRequest.getStudentName());
    }

    @Override
    public TokenResponseData loginStudent(StudentCredentialRequest studentCredentialRequest) {
        try {
            Student student = studentRepository.getStudentByUsername(studentCredentialRequest.getUsername()).orElse(null);

            if (student == null) {
                throw new NotFoundException("Student not found");
            }

            String token = jwtUtil.generateToken("mysubject");
            jwtUtil.addTokenToStorage(token);
            return new TokenResponseData(student.getId(), token);
        } catch (NotFoundException e) {
            throw new UnauthorizedException("Invalid username or password");
        }
    }

    @Override
    public StudentResponseData getStudentByUsername(String username) {
        Student student = studentRepository.getStudentByUsername(username).orElse(null);

        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        return new StudentResponseData(student.getId(), student.getUsername(), student.getStudentName());
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
