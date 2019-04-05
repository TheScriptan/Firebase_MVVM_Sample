package com.example.firebaseexercises.business.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


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
        this.id = null;
        this.username = username;
        this.content = content;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("username", username);
        result.put("content", content);
        return result;
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

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
