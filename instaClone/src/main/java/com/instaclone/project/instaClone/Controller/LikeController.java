package com.instaclone.project.instaClone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instaclone.project.instaClone.Entities.Like;
import com.instaclone.project.instaClone.Entities.Post;
import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.repo.LikeRepository;
import com.instaclone.project.instaClone.repo.PostRepository;
import com.instaclone.project.instaClone.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LikeController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/likes")
    @ResponseBody
    @Transactional
    public ResponseEntity<Void> likePost(@RequestParam("postId") Long postId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            User currentUser = userService.getUserById(userId);
            Like existingLike = post.getLikes().stream()
                .filter(like -> like.getUser().getUserId().equals(currentUser.getUserId()))
                .findFirst()
                .orElse(null);
            if (existingLike != null) {
                post.getLikes().remove(existingLike);
                likeRepository.delete(existingLike);
            } else {
                Like newLike = new Like(currentUser, post);
                post.getLikes().add(newLike);
                likeRepository.save(newLike);
            }
            postRepository.save(post);
        }
        return ResponseEntity.ok().build();
    }
}
