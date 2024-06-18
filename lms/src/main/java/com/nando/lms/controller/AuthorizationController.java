package com.nando.lms.controller;

import com.nando.lms.model.request.StudentCredentialRequest;
import com.nando.lms.model.request.StudentRequest;
import com.nando.lms.model.request.TokenRequest;
import com.nando.lms.model.response.SuccessResponse;
import com.nando.lms.model.response.data.StudentResponseData;
import com.nando.lms.model.response.data.TokenResponseData;
import com.nando.lms.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UrlMapping.AUTH)
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/registration")
    public ResponseEntity registerStudent(@RequestBody StudentRequest studentRequest) {
        StudentResponseData studentResponseData = authorizationService.registerStudent(studentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), studentResponseData));
    }

    @PostMapping("/login")
    public ResponseEntity loginStudent(@RequestBody StudentCredentialRequest credentialRequest) {
        TokenResponseData tokenResponseData = authorizationService.loginStudent(credentialRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), tokenResponseData));
    }

    @PostMapping("/logout")
    public ResponseEntity logoutStudent(@RequestBody TokenRequest tokenRequest) {
        boolean isLogout = authorizationService.logoutStudent(tokenRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), isLogout));
    }
}
