package com.instaclone.project.instaClone.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instaclone.project.instaClone.Entities.Comment;
import com.instaclone.project.instaClone.Entities.Post;
import com.instaclone.project.instaClone.Entities.User;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findByPostId(Long postId);

    List<Comment> findByUser(User user); 

    

    

}


