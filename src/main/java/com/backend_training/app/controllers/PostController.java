package com.backend_training.app.controllers;

import com.backend_training.app.models.Post;
import com.backend_training.app.ratelimiter.RateLimit;
import com.backend_training.app.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/resources/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam(defaultValue = "10") int limit, @RequestParam(required = false) String cursor) throws Exception {
        return ResponseEntity.ok(postService.fetchPosts(cursor, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping
    @RateLimit(capacity = 10, refillTokens = 1, duration = 60 * 1000)
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @PutMapping
    public ResponseEntity<?> updatePost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable UUID id) {
        return ResponseEntity.ok(postService.deletePost(id));
    }
}