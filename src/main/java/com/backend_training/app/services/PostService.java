package com.backend_training.app.services;

import com.backend_training.app.dto.PostResponse;
import com.backend_training.app.models.Post;
import com.backend_training.app.repositories.PostRepository;
import com.backend_training.app.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    private final EncryptionUtil encryptionUtil;

    @Autowired
    public PostService(EncryptionUtil encryptionUtil) {
        this.encryptionUtil = encryptionUtil;
    }

    public Post createPost(Post post, String userID) {
        post.setUserID(userID);
        postRepository.save(post);
        return post;
    }

    public PostResponse fetchPosts(String cursor, int limit) throws Exception {

        List<Post> posts;

        if (cursor == null) {
            posts = postRepository.findTopNPosts(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt")));
        } else {
            String decryptedCursor = encryptionUtil.decrypt(cursor);
            posts = postRepository.findByIdLessThan(UUID.fromString(decryptedCursor), PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt")));
        }

        String nextCursor = posts.isEmpty() ? null : String.valueOf(posts.get(posts.size() - 1).getId());
        String encryptedNextCursor = nextCursor != null ? encryptionUtil.encrypt(nextCursor) : null;

        return new PostResponse(posts, encryptedNextCursor);
    }
}