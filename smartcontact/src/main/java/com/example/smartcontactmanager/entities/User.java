package com.example.smartcontactmanager.entities;

public class User {
    private int id;
    private String FirstName;
    private String LastName;

    private String email;
    private String password;
    private String role;
    private Long score;
    
    public Long getScore() {
        return score;
    }


    public void setScore(Long score) {
        this.score = score;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

    private String contact;

    public User() {
        super();
    }


    public int getId() {
        return id;
    }

    public void setId() {
        this.id=(int)(Math.random()*10000);
    }

    public String getContact(){
        return contact;
    }

    public void setContact(String contact){
        this.contact=contact;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String name) {
        this.FirstName = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String name) {
        this.LastName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
public String toString() {
    return "User{" +
            "id=" + id +
            ", FirstName='" + FirstName + '\'' +
            ", LastName='" + LastName + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", contact='" + contact + '\'' +
            ", role=" + role + '\'' +
            '}';
}

}
