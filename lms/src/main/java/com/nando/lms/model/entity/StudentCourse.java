package com.nando.lms.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students_courses")
@Data
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
