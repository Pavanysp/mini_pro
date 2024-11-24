package com.example.ESD.model;

import java.util.List;

public class GradeRequest {

    private List<Integer> studentIds;  // List of student IDs
    private List<String> grades;       // List of grades corresponding to each student

    // Getters and Setters
    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }
}
