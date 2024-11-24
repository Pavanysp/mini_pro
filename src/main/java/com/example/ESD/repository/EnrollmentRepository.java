package com.example.ESD.repository;

import com.example.ESD.model.Enrollment;
import com.example.ESD.model.Student;
import com.example.ESD.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    // Find an enrollment by student and course
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    // You can add more methods as necessary, like finding enrollments by studentId or courseId
}
