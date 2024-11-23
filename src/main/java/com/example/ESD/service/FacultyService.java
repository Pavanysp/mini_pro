package com.example.ESD.service;

import com.example.ESD.model.Course;
import com.example.ESD.model.Student;
import com.example.ESD.model.Teaching;
import com.example.ESD.repository.CourseRepository;
import com.example.ESD.repository.StudentRepository;
import com.example.ESD.repository.TeachingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    @Autowired
    private TeachingRepository teachingRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Get courses taught by the faculty
    public List<Course> getCoursesByFaculty(String username) {
        List<Teaching> teachings = teachingRepository.findByFaculty_Username(username);
        return teachings.stream()
                .map(teaching -> teaching.getCourse())  // Get the course associated with each teaching
                .collect(Collectors.toList());
    }

    // Get students in a particular course
    public List<Student> getStudentsInCourse(String username, int courseId) {
        List<Teaching> teachings = teachingRepository.findByFaculty_Username(username);
        if (teachings.stream().anyMatch(teaching -> teaching.getCourse().getCid() == courseId)) {
            return studentRepository.findByEnrollments_Course_Cid(courseId);  // Get students by course ID using Enrollment
        } else {
            throw new RuntimeException("You don't teach this course.");
        }
    }

    // Grade a student
    public String gradeStudent(String username, int courseId, int studentId, String grade) {
        // Logic to grade a student
        return "Graded student " + studentId + " in course " + courseId + " with grade " + grade;
    }

    // Grade multiple students
    public String gradeStudents(String username, int courseId, List<Integer> studentIds) {
        studentIds.forEach(studentId -> gradeStudent(username, courseId, studentId, "A"));  // Default grade for simplicity
        return "Graded " + studentIds.size() + " students in course " + courseId;
    }
}