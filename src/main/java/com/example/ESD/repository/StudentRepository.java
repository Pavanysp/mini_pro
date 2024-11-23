package com.example.ESD.repository;

import com.example.ESD.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    // Use the Enrollment entity to find students by courseId
    List<Student> findByEnrollments_Course_Cid(int courseId);
}
