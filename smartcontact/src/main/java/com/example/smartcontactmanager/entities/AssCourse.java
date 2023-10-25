package com.example.smartcontactmanager.entities;

// import jakarta.persistence.*;
// import java.util.Date;

// @Entity
// @Table(name = "AssCourse")
public class AssCourse {
    // @Id
    // @Column(name = "id")
    private Long id;

    // @Column(name = "AssignmentName")
    private String assName;

    // @Column(name = "CourseID")
    private Long courseid;

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getAssName() {
        return assName;
    }



    public void setAssName(String assName) {
        this.assName = assName;
    }


    public AssCourse(){
        super();
    }



    public Long getCourseid() {
        return courseid;
    }


    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }
    
}

