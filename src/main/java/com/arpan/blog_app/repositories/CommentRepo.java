package com.arpan.blog_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpan.blog_app.entities.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer> {

    
    
}
