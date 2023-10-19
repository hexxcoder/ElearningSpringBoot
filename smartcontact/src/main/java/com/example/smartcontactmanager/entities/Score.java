package com.example.smartcontactmanager.entities;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScoreID")
    private Long scoreID;

    @ManyToOne
    @JoinColumn(name = "AssignmentID")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "email")
    private User email;

    @Column(name = "Score")
    private double score;

    @Column(name = "Grading_Date")
    private Date gradingDate;

    public Score() {
        super();
    }

    public Long getScoreID() {
        return scoreID;
    }

    public void setScoreID(Long scoreID) {
        this.scoreID = scoreID;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getGradingDate() {
        return gradingDate;
    }

    public void setGradingDate(Date gradingDate) {
        this.gradingDate = gradingDate;
    }

    @Override
    public String toString() {
        return "Score [scoreID=" + scoreID + ", assignment=" + assignment + ", email=" + email + ", score=" + score
                + ", gradingDate=" + gradingDate + "]";
    }

    // Getters and setters
}
