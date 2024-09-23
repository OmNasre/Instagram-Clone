package com.instaclone.project.instaClone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.instaclone.project.instaClone.Entities.User;
import com.instaclone.project.instaClone.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (userService.isValidUser(username, password)) {
            User user = userService.getUserByUsername(username);
            session.setAttribute("userId", user.getUserId());

            // Check user role and redirect accordingly
            
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin"; // Redirect to admin page
            } else {
                return "redirect:/posts"; // Redirect to user posts page
                
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/register")
    public String registerPage(Model model) {
        return "register";
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.usernameExists(username)) {
            model.addAttribute("error", "Username already taken");
            return "register";
        }
        
        // Create new user and save to the database
        User newUser = new User();
        
        newUser.setUsername(username);
        newUser.setPassword(password); // Password should ideally be hashed
        
        userService.save(newUser);
        
        // Redirect to login page after successful registration
        return "redirect:/login";
    }
}
