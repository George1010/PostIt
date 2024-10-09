package com.backend_training.app.controllers;

import com.backend_training.app.dto.PostResponse;
import com.backend_training.app.models.Post;
import com.backend_training.app.services.PostService;
import com.backend_training.app.services.RateLimiterService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/resources/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam(defaultValue = "10") int limit, @RequestParam(required = false) String cursor) throws Exception {
        return ResponseEntity.ok(postService.fetchPosts(cursor, limit));
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            return ResponseEntity.ok(postService.createPost(post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@RequestBody Post post) {
        try {
            return ResponseEntity.ok(postService.updatePost(post));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deletePost(@RequestParam String id) {
        try {
            return ResponseEntity.ok(postService.deletePost(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}