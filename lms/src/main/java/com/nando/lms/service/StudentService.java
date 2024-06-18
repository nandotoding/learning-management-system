package com.nando.lms.service;

import com.nando.lms.model.entity.Course;
import com.nando.lms.model.entity.Student;
import com.nando.lms.model.request.StudentRequest;
import com.nando.lms.model.response.data.StudentCourseResponseData;
import com.nando.lms.model.response.data.StudentResponseData;

import java.util.List;

public interface StudentService {
    public List<StudentResponseData> getAllStudents(String token);
    public StudentResponseData getStudentById(String id, String token);
    public StudentResponseData addStudent(StudentRequest studentRequest);
    public StudentCourseResponseData getStudentWithCourses(String id, String token);
    public StudentCourseResponseData addStudentCourse(Student student, Course course, String token);
    public StudentResponseData getStudentByUsername(String username);
}
