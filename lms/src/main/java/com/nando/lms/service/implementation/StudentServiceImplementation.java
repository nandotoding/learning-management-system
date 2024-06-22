package com.nando.lms.service.implementation;

import com.nando.lms.exception.BadRequestException;
import com.nando.lms.exception.NotFoundException;
import com.nando.lms.exception.UnauthorizedException;
import com.nando.lms.model.entity.Course;
import com.nando.lms.model.entity.Student;
import com.nando.lms.model.request.StudentRequest;
import com.nando.lms.model.response.data.StudentCourseResponseData;
import com.nando.lms.model.response.data.StudentResponseData;
import com.nando.lms.repository.StudentCourseRepository;
import com.nando.lms.repository.StudentRepository;
import com.nando.lms.service.StudentService;
import com.nando.lms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImplementation implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public StudentServiceImplementation(StudentRepository studentRepository, StudentCourseRepository studentCourseRepository, JwtUtil jwtUtil) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<StudentResponseData> getAllStudents(String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

        List<Student> studentList = studentRepository.getAllStudents();
        List<StudentResponseData> studentResponseDataList = new ArrayList<>();

        for (Student s : studentList) {
            StudentResponseData studentResponseData = new StudentResponseData();
            studentResponseData.setId(s.getId());
            studentResponseData.setUsername(s.getUsername());
            studentResponseData.setStudentName(s.getStudentName());
            studentResponseDataList.add(studentResponseData);
        }

        return studentResponseDataList;
    }

    @Override
    public StudentResponseData getStudentById(String id, String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

        Student student = studentRepository.getStudentById(id).orElse(null);

        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        StudentResponseData studentResponseData = new StudentResponseData();
        studentResponseData.setId(student.getId());
        studentResponseData.setUsername(student.getUsername());
        studentResponseData.setStudentName(student.getStudentName());
        return studentResponseData;
    }

    @Override
    public StudentResponseData addStudent(StudentRequest studentRequest, String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

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
    public StudentCourseResponseData getStudentWithCourses(String id, String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

        Student student = studentRepository.getStudentById(id).orElse(null);

        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        List<Course> courses = studentCourseRepository.getByStudentId(id);
        return new StudentCourseResponseData(id, student.getUsername(), student.getStudentName(), courses);
    }

    @Override
    public StudentCourseResponseData addStudentCourse(Student student, Course course, String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

        studentCourseRepository.addStudentCourse(UUID.randomUUID().toString(), student.getId(), course.getId());
        return getStudentWithCourses(student.getId(), token);
    }

    @Override
    public StudentResponseData getStudentByUsername(String username, String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

        Student student = studentRepository.getStudentByUsername(username).orElse(null);

        if (student == null) {
            throw new NotFoundException("Student not found");
        }

        return new StudentResponseData(student.getId(), student.getUsername(), student.getStudentName());
    }

    @Override
    public List<StudentResponseData> searchStudentsByName(String name, String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

        List<StudentResponseData> studentResponseDataList = new ArrayList<>();
        List<Student> studentList = studentRepository.searchStudentsByName(name);

        for (Student s : studentList) {
            studentResponseDataList.add(new StudentResponseData(s.getId(), s.getUsername(), s.getStudentName()));
        }

        return studentResponseDataList;
    }
}
