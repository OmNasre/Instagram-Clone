package com.instaclone.project.instaClone.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.instaclone.project.instaClone.Entities.Comment;
import com.instaclone.project.instaClone.Entities.Post;
import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.repo.CommentRepository;
import com.instaclone.project.instaClone.repo.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    // Fetch all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get a post by ID
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    // Save a new post
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // Delete a post by ID
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // Add a comment to a post
    @Transactional
    public void addComment(Long postId, String commentText, User user) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));
        
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setUser(user);
        comment.setPost(post); // Set the relationship with the post
        
        commentRepository.save(comment); // Save the comment using CommentRepository
    }
    public List<Post> getPostsByUserId(Long userId) {
        User user = new User();
        user.setUserId(userId);
        return postRepository.findAllByUser(user); // Use the new repository method
    }
}
