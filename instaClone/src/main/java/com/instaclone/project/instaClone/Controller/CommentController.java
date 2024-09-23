package com.instaclone.project.instaClone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.instaclone.project.instaClone.Entities.Comment;
import com.instaclone.project.instaClone.Entities.Post;
import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.services.CommentService;
import com.instaclone.project.instaClone.services.PostService;
import com.instaclone.project.instaClone.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/comments")
    public String addComment(@RequestParam String text, @RequestParam Long postId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        Post post = postService.getPostById(postId);
        if (post != null) {
            User currentUser = userService.getUserById(userId);
            Comment newComment = new Comment(text, currentUser, post);
            commentService.addComment(newComment);
        }
        return "redirect:/posts";
    }

    @PostMapping("/comments/edit")
    @Transactional
    public ResponseEntity<Void> editComment(@RequestParam Long id, @RequestParam String text, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Comment comment = commentService.getCommentById(id);
        if (comment != null && comment.getUser().getUserId().equals(userId)) {
            comment.setText(text);
            commentService.addComment(comment);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/comments/delete")
    public ResponseEntity<?> deleteComment(@RequestParam Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        try {
            commentService.deleteCommentById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting comment: " + e.getMessage());
        }
    }
}
