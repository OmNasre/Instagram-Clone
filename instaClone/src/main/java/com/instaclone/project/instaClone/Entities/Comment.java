package com.instaclone.project.instaClone.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    public Comment() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(String text, User user, Post post) {
        this.text = text;
        this.user = user;
        this.post = post;
    }

    // Getters and Setters
    public Long getId() { return id; } // Updated getter
    public void setId(Long id) { this.id = id; } // Updated setter

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
