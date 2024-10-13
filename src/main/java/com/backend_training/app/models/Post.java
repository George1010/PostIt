package com.backend_training.app.models;

import java.time.LocalDateTime;
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
    private boolean deleted = false;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Post() {
    }
    public Post(String title, String content, String userID) {
        this.title = title;
        this.content = content;
        this.userID = userID;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
