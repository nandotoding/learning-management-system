package com.nando.lms.model.response.data;

import com.nando.lms.model.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseResponseData {
    private String studentId;
    private String studentUsername;
    private String studentName;
    private List<Course> courses;
}
