package com.example.smartcontactmanager.entities;

// import jakarta.persistence.*;
// import java.util.Date;

// @Entity
// @Table(name = "NotesCourse")
public class NotesCourse {
    //@Id
    //@Column(name = "NoteID")
    private Long noteID;

    //@Column(name = "Title")
    private String title;

    //@Column(name = "CourseID")
    private Long courseId;

    public Long getNoteID() {
        return noteID;
    }

    public void setNoteID(Long noteID) {
        this.noteID = noteID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    // Getters and setters
}
