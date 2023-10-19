package com.example.smartcontactmanager.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AssignmentID")
    private Long assignmentID;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @Column(name = "Title")
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Due_Date")
    private Date dueDate;

    public Assignment(){
        super();
    }

    public Long getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(Long assignmentID) {
        this.assignmentID = assignmentID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Assignment [assignmentID=" + assignmentID + ", course=" + course + ", title=" + title + ", description="
                + description + ", dueDate=" + dueDate + "]";
    }

    // Getters and setters
}

