package com.instaclone.project.instaClone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instaclone.project.instaClone.Entities.Comment;
import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.repo.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private UserService userService;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
    
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id); // Delete the comment by ID
    }
    public List<Comment> getCommentsById(Long userId) {
        // Assuming you want to find comments by user ID using the User entity
        User user = userService.getUserById(userId); 
        System.out.println(user.getUsername());// Ensure you have a method to get User by ID
        return commentRepository.findByUser(user); // Use the method that takes a User object
    }


}
