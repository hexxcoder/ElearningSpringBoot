package com.example.smartcontactmanager.entities;

public class Course {

    private Long courseID;

    private String courseName;

    private String description;

    private Long price;

    public Course(Long courseID, String courseName, String description, Long price) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.price = price;
    }

    public Long getPrice() {
        return price;

    }

    public void setPrice(Long score) {
        this.price = score;
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
}