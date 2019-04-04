package com.example.firebaseexercises.business.model;

public class Message {

    private String id;
    private String username;
    private String content;

    public Message() {}

    public Message(String id, String username, String content){
        this.id = id;
        this.username = username;
        this.content = content;
    }

    public Message(String username, String content){
        this.username = username;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
