package com.nando.lms.controller;

import com.nando.lms.model.response.SuccessResponse;
import com.nando.lms.model.response.data.StudentResponseData;
import com.nando.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.STUDENT)
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity getAllStudents(@RequestHeader(name = "Authorization") String authorizationHeader) {
        List<StudentResponseData> studentResponseData = studentService.getAllStudents(authorizationHeader.replace("Bearer ", ""));
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), studentResponseData));
    }
}
