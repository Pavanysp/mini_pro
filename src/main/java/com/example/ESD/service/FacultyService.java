package com.example.ESD.service;

import com.example.ESD.model.Course;
import com.example.ESD.model.Enrollment;
import com.example.ESD.model.Student;
import com.example.ESD.model.Teaching;
import com.example.ESD.repository.CourseRepository;
import com.example.ESD.repository.EnrollmentRepository;
import com.example.ESD.repository.StudentRepository;
import com.example.ESD.repository.TeachingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ESD.exceptions.UserNotFoundException;
import com.example.ESD.exceptions.*;

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
    @Autowired
    private EnrollmentRepository enrollmentRepository;

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
            throw new CourseNotFoundException("You don't teach this course.");
        }
    }

    // Grade a student
    public String gradeStudents(String username, int courseId, List<Integer> studentIds, List<String> grades) {
        if (studentIds.size() != grades.size()) {
            throw new InvalidInputException("Number of students and grades must match.");
        }

        // Define valid grade patterns
        String validGradeRegex = "^[A-FS][+-]?$";

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < studentIds.size(); i++) {
            int studentId = studentIds.get(i);
            String grade = grades.get(i);

            // Validate grade format
            if (!grade.matches(validGradeRegex)) {
                throw new InvalidInputException("Invalid grade: " + grade + ". Grades must be +X, -X, or X where X is A, B, C, D, E, F, or S.");
            }

            // Grade each student
            String gradingResult = gradeStudent(username, courseId, studentId, grade);
            result.append(gradingResult).append("\n");
        }

        return "Graded " + studentIds.size() + " students in course " + courseId + "\n" + result.toString();
    }

    // Single student grading method
    public String gradeStudent(String username, int courseId, int studentId, String grade) {
        // Retrieve student and course by their IDs
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        // Find the enrollment (student-course relation)
        Enrollment enrollment = enrollmentRepository.findByStudentAndCourse(student, course)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found"));
        String validGradeRegex = "^[A-FS][+-]?$";
        if (!grade.matches(validGradeRegex)) {
            throw new InvalidInputException("Invalid grade: " + grade + ". Grades must be +X, -X, or X where X is A, B, C, D, E, F, or S.");
        }
        // Set the grade for the student
        enrollment.setGrade(grade);

        // Save the updated enrollment
        enrollmentRepository.save(enrollment);

        return "Graded student " + studentId + " in course " + courseId + " with grade " + grade;
    }


}
