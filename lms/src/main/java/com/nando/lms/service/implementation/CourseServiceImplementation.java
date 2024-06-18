package com.nando.lms.service.implementation;

import com.nando.lms.exception.NotFoundException;
import com.nando.lms.exception.UnauthorizedException;
import com.nando.lms.model.entity.Course;
import com.nando.lms.repository.CourseRepository;
import com.nando.lms.service.CourseService;
import com.nando.lms.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {
    private final CourseRepository courseRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public CourseServiceImplementation(CourseRepository courseRepository, JwtUtil jwtUtil) {
        this.courseRepository = courseRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<Course> getAllCourse(String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

        return courseRepository.getAllCourse();
    }

    @Override
    public Course getCourseById(String id, String token) {
        if (jwtUtil.isTokenRevoked(token)) {
            throw new UnauthorizedException("Token revoked");
        }

        Course course = courseRepository.getCourseById(id).orElse(null);

        if (course == null) {
            throw new NotFoundException("Course not found");
        }

        return course;
    }
}
