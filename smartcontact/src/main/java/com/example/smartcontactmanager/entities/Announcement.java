package com.example.smartcontactmanager.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Announcement")
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnnouncementID")
    private Long announcementID;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @Column(name = "Title")
    private String title;

    @Column(name = "Content")
    private String content;

    @Column(name = "Post_Date")
    private Date postDate;

    public Long getAnnouncementID() {
        return announcementID;
    }

    public void setAnnouncementID(Long announcementID) {
        this.announcementID = announcementID;
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

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Announcement(){
        super();
    }

    @Override
    public String toString() {
        return "Announcement [announcementID=" + announcementID + ", course=" + course + ", title=" + title
                + ", content=" + content + ", postDate=" + postDate + "]";
    }

    // Getters and setters
}

