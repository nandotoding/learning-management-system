package com.nando.lms.repository;

import com.nando.lms.model.dto.CourseDTO;
import com.nando.lms.model.entity.Course;
import com.nando.lms.model.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, String> {
    @Query(value = "SELECT c.id, c.course_name FROM students_courses sc " +
            "INNER JOIN courses c ON sc.course_id = c.id " +
            "WHERE sc.student_id = :id", nativeQuery = true)
    public List<CourseDTO> getByStudentId(String id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO students_courses (id, student_id, course_id) VALUES (:id, :studentId, :courseId)", nativeQuery = true)
    public void addStudentCourse(String id, String studentId, String courseId);

    @Query(value = "SELECT * FROM students_courses sc WHERE sc.student_id = :studentId AND sc.course_id = :courseId", nativeQuery = true)
    public List<StudentCourse> getByStudentIdAndCourseId(String studentId, String courseId);
}
