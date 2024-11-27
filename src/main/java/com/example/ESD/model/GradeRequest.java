package com.example.ESD.model;

import java.util.List;

public class GradeRequest {

    private List<Integer> studentIds;  // List of student IDs
    private List<String> grades;       // List of grades corresponding to each student

    // Getters and Setters
    private String grade;
    private int studentId; // Add this field

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    // Getters and Setters
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
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
