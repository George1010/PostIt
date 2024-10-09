package com.backend_training.app.dto;

import com.backend_training.app.models.Post;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PostResponse(List<Post> posts, String nextCursor, String errorMessage) {
    public PostResponse(List<Post> posts, String nextCursor) {
        this(posts, nextCursor, null);
    }
}