package com.example.smartcontactmanager.entities;

// import java.util.Date;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "Course")
public class Course {
    // @Id
    // @Column(name = "CourseID")
    private Long courseID;

    // @Column(name = "Course_Name")
    private String courseName;

    // @Column(name = "Description")
    private String description;

    public Course(Long courseID, String courseName, String description) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
    }

    public Long getCourseID() {
        return courseID;
    }

    public void setCourseID(Long courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course [courseID=" + courseID + ", courseName=" + courseName + ", description=" + description
                + "]";
    }

    // Getters and setters
}