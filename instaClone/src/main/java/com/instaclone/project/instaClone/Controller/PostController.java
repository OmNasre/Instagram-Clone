package com.instaclone.project.instaClone.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.instaclone.project.instaClone.Entities.Post;
import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.repo.PostRepository;
import com.instaclone.project.instaClone.services.PostService;
import com.instaclone.project.instaClone.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public String showPosts(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        List<Post> posts = postRepository.findAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        model.addAttribute("userId", userId);
        return "posts";
    }

    @PostMapping("/add-post")
    public String addPost(@RequestBody Post post, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        User user = userService.getUserById(userId);
        
        post.setUser(user);
        postRepository.save(post);
        return "redirect:/posts";
    }

    @DeleteMapping("/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("userId");
        if (loggedInUserId == null) {
            return "redirect:/login";
        }
        Post post = postService.getPostById(postId);
        if (post.getUser().getUserId().equals(loggedInUserId)) {
            postService.deletePost(postId);
        }
        return "redirect:/posts";
    }
}
