package com.example.smartcontactmanager.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CourseID")
    private Long courseID;

    @Column(name = "Course_Name")
    private String courseName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Start_Date")
    private Date startDate;

    @Column(name = "End_Date")
    private Date endDate;

    public Course() {
        super();
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Course [courseID=" + courseID + ", courseName=" + courseName + ", description=" + description
                + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }

    // Getters and setters
}