package com.nando.lms.repository;

import com.nando.lms.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO courses (id, course_name) VALUES (:id, :name)", nativeQuery = true)
    public void addCourse(String id, String name);

    @Query(value = "SELECT * FROM courses c WHERE c.id = :id", nativeQuery = true)
    public Optional<Course> getCourseById(String id);

    @Query(value = "SELECT * FROM courses", nativeQuery = true)
    public List<Course> getAllCourse();
}
