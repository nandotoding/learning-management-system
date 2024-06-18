package com.nando.lms.service;

import com.nando.lms.model.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getAllCourse(String token);
    public Course getCourseById(String id, String token);
}
