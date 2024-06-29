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
    public StudentResponseData addStudent(StudentRequest studentRequest, String token);
    public StudentCourseResponseData getStudentWithCourses(String username, String token);
    public StudentCourseResponseData addStudentCourse(String studentUsername, String courseId, String token);
    public StudentResponseData getStudentByUsername(String username, String token);
    public List<StudentResponseData> searchStudentsByName(String name, String token);
}
