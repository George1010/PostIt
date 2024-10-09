package com.backend_training.app.models;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {


    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String content;
    private String userID;

    private Date createdAt = new Date();

    public Post() {
    }
    public Post(String title, String content, String userID, Date createdAt) {
        this.title = title;
        this.content = content;
        this.userID = userID;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserID() {
        return userID.toString();
    }

    public void setUserID(String userId) {
        this.userID = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", username='" + userID + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
