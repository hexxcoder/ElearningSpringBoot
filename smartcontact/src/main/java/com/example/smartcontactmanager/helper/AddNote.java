package com.example.smartcontactmanager.helper;

public class AddNote {

    private Long id;
    private Long cid;
    private String name;
    public AddNote(Long id, Long cid, String name) {
        this.id = id;
        this.cid = cid;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCid() {
        return cid;
    }
    public void setCid(Long cid) {
        this.cid = cid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    
    
}
