package com.example.smartcontactmanager.entities;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// @Entity
// @Table(name="Assstat")
public class Assstat {

    // @Id
    private String email;

    // @Id
    private Long assid;

    private Long courseid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAssid() {
        return assid;
    }

    public void setAssid(Long assid) {
        this.assid = assid;
    }

    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    
    
}
