package com.example.smartcontactmanager.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NoteID")
    private Long noteID;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @Column(name = "Title")
    private String title;

    @Column(name = "Content")
    private String content;

    @Column(name = "Upload_Date")
    private Date uploadDate;

    public Notes() {
        super();
    }

    public Long getNoteID() {
        return noteID;
    }

    public void setNoteID(Long noteID) {
        this.noteID = noteID;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "Notes [noteID=" + noteID + ", course=" + course + ", title=" + title + ", content=" + content
                + ", uploadDate=" + uploadDate + "]";
    }

    // Getters and setters
}
