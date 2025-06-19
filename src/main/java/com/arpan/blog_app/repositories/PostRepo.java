package com.arpan.blog_app.repositories;

import java.util.List;
import com.arpan.blog_app.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arpan.blog_app.entities.Post;
import com.arpan.blog_app.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByUser(Category category);

}
