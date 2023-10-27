package com.example.smartcontactmanager.helper;

public class AddAss {

    private long id;
    private long cid;
    private long score;
   
    private String name;

    public AddAss(long id, long cid, long score, String name) {
        this.id = id;
        this.cid = cid;
        this.score = score;
        this.name = name;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getCid() {
        return cid;
    }
    public void setCid(long cid) {
        this.cid = cid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
     public long getScore() {
        return score;
    }
    public void setScore(long score) {
        this.score = score;
    }
}
