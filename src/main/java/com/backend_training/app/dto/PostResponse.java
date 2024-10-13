package com.backend_training.app.dto;

import com.backend_training.app.models.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    private List<Post> posts;
    private String nextCursor;

    private String errorMessage;

    public PostResponse(List<Post> posts, String nextCursor) {
        this.posts = posts;
        this.nextCursor = nextCursor;
    }
    
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
