package com.instaclone.project.instaClone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.instaclone.project.instaClone.Entities.Post;
import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.services.PostService;
import com.instaclone.project.instaClone.services.UserService;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<User> users = userService.getAllUsers();
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("users", users);
        model.addAttribute("posts", posts);
        return "admin"; // Return the admin view
    }

    @PostMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam Long userId) {
        userService.deleteUserById(userId);
        return "redirect:/admin"; // Redirect back to admin page after deletion
    }

    @PostMapping("/admin/deletePost")
    public String deletePost(@RequestParam Long postId) {
        postService.deletePost(postId);
        return "redirect:/admin"; // Redirect back to admin page after deletion
    }
}
