package com.example.ESD.controller;

import com.example.ESD.model.Course;
import com.example.ESD.model.GradeRequest;
import com.example.ESD.model.Student;
import com.example.ESD.model.Teaching;
import com.example.ESD.service.FacultyService;
import com.example.ESD.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private JwtUtil jwtUtil;

    // Get courses taught by the faculty (requires login)
    @GetMapping("/courses")
    public List<Course> getCourses() {
        String username = getAuthenticatedUser();
        return facultyService.getCoursesByFaculty(username);
    }

    // Get list of students enrolled in a particular course
    @GetMapping("/courses/{courseId}/students")
    public List<Student> getStudentsInCourse(@PathVariable int courseId) {
        String username = getAuthenticatedUser();
        return facultyService.getStudentsInCourse(username, courseId);
    }

    // Grade a student
    @PostMapping("/courses/{courseId}/grade/{studentId}")
    public String gradeStudent(@PathVariable int courseId, @PathVariable int studentId, @RequestBody String grade) {
        String username = getAuthenticatedUser();
        return facultyService.gradeStudent(username, courseId, studentId, grade);
    }

    // Grade multiple students
    @PostMapping("/courses/{courseId}/grade")
    public String gradeStudents(@PathVariable int courseId, @RequestBody GradeRequest gradeRequest) {
        String username = getAuthenticatedUser();
        return facultyService.gradeStudents(username, courseId, gradeRequest.getStudentIds()); // Use gradeRequest.getStudentIds()
    }

    // Helper method to get the authenticated username from the JWT token
    private String getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // The username is stored in the 'name' field of the authentication object
    }
}
