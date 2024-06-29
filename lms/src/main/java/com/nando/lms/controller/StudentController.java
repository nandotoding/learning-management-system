package com.nando.lms.controller;

import com.nando.lms.model.request.CourseRequest;
import com.nando.lms.model.request.SearchRequest;
import com.nando.lms.model.response.SuccessResponse;
import com.nando.lms.model.response.data.StudentCourseResponseData;
import com.nando.lms.model.response.data.StudentResponseData;
import com.nando.lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/search")
    public ResponseEntity searchStudentsByUsername(@RequestBody SearchRequest searchRequest, @RequestHeader(name = "Authorization") String authorizationHeader) {
        List<StudentResponseData> studentResponseDataList = studentService.searchStudentsByName(searchRequest.getQuery(), authorizationHeader.replace("Bearer ", ""));
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), studentResponseDataList));
    }

    @GetMapping("/{username}")
    public ResponseEntity getStudentWithCourses(@PathVariable(name = "username") String username, @RequestHeader(name = "Authorization") String authorizationHeader) {
        StudentCourseResponseData studentCourseResponseData = studentService.getStudentWithCourses(username, authorizationHeader.replace("Bearer ", ""));
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), studentCourseResponseData));
    }

    @PostMapping("/{username}/add-course")
    public ResponseEntity addStudentCourse(@PathVariable(name = "username") String username, @RequestBody CourseRequest courseRequest, @RequestHeader(name = "Authorization") String authorizationHeader) {
        StudentCourseResponseData studentCourseResponseData = studentService.addStudentCourse(username, courseRequest.getCourseId(), authorizationHeader.replace("Bearer ", ""));
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), studentCourseResponseData));
    }
}
