package com.example.smartcontactmanager.entities;

public class AssCourse {

    private Long id;

    private String assName;

    private Long courseid;

    private Long score;

    public Long getScore() {
        return score;
    }



    public void setScore(Long score) {
        this.score = score;
    }



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

