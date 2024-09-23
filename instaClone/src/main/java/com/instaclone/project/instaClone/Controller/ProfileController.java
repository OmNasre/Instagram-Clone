package com.instaclone.project.instaClone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.instaclone.project.instaClone.Entities.Comment;
import com.instaclone.project.instaClone.Entities.Post;
import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.repo.PostRepository;
import com.instaclone.project.instaClone.services.CommentService;
import com.instaclone.project.instaClone.services.PostService;
import com.instaclone.project.instaClone.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;
    
    @Autowired
    private PostRepository postRepository;

    // Display user profile with posts and comments, only if logged in
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        // Redirect to login if user is not logged in
        if (userId == null) {
            return "redirect:/login";
        }

        User user = userService.getUserById(userId);
        List<Comment> comments = commentService.getCommentsById(userId);
        List<Post> posts = postService.getPostsByUserId(user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        model.addAttribute("comments", comments);

        return "profile";
    }

    


}
