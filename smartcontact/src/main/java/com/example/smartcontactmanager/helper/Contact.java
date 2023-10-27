package com.example.smartcontactmanager.helper;

public class Contact {

    private Long id;
    private String name;
    private String email;
    private String subject;
    private String detail;
    
    public Contact(Long id, String name, String email, String subject, String detail) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.detail = detail;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
   
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
}
