package com.nando.lms.repository;

import com.nando.lms.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO students (id, username, password, student_name) VALUES (:id, :username, :password, :name)", nativeQuery = true)
    public void addStudent(String id, String username, String password, String name);

    @Query(value = "SELECT * FROM students s WHERE s.id = :id", nativeQuery = true)
    public Optional<Student> getStudentById(String id);

    @Query(value = "SELECT * FROM students", nativeQuery = true)
    public List<Student> getAllStudents();

    @Query(value = "SELECT * FROM students s WHERE s.username = :username", nativeQuery = true)
    public Optional<Student> getStudentByUsername(String username);

    @Query(value = "SELECT * FROM students s WHERE s.student_name ILIKE %:name%", nativeQuery = true)
    public List<Student> searchStudentsByName(String name);
}
