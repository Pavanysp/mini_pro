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
import java.util.stream.Collectors;

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
    public String gradeStudent(
            @PathVariable int courseId,
            @PathVariable int studentId,
            @RequestBody GradeRequest gradeRequest) {
        String username = getAuthenticatedUser();
        String grade = gradeRequest.getGrade(); // Extract the grade value
        return facultyService.gradeStudent(username, courseId, studentId, grade);
    }

    // Grade multiple students
    @PostMapping("/courses/{courseId}/grade")
    public String gradeStudents(
            @PathVariable int courseId,
            @RequestBody List<GradeRequest> gradeRequests) {
        String username = getAuthenticatedUser(); // Get the authenticated faculty user
        List<Integer> studentIds = gradeRequests.stream()
                .map(GradeRequest::getStudentId) // Assuming the request includes student IDs
                .collect(Collectors.toList());
        List<String> grades = gradeRequests.stream()
                .map(GradeRequest::getGrade)
                .collect(Collectors.toList());

        return facultyService.gradeStudents(username, courseId, studentIds, grades);
    }

    // Helper method to get the authenticated username from the JWT token
    private String getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // The username is stored in the 'name' field of the authentication object
    }
}
