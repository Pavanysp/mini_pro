package com.example.ESD.model;

import jakarta.persistence.*;

@Entity
public class Teaching {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teachingId;

    // Many-to-One relationship with Faculty
    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "fid")  // The foreign key column in 'Teaching' table
    private Faculty faculty;  // Faculty who teaches the course

    // Many-to-One relationship with Course
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "cid")  // The foreign key column in 'Teaching' table
    private Course course;  // The course being taught

    // Getters and Setters
    public int getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(int teachingId) {
        this.teachingId = teachingId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Teaching{" +
                "teachingId=" + teachingId +
                ", faculty=" + faculty +
                ", course=" + course +
                '}';
    }
}
