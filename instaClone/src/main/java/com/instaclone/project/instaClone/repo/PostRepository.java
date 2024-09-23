package com.instaclone.project.instaClone.repo;

import com.instaclone.project.instaClone.Entities.Post;
import com.instaclone.project.instaClone.Entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findAllByUser(User user);
}
 