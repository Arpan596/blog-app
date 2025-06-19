package com.arpan.blog_app.services;

import java.util.List;

import com.arpan.blog_app.entities.Post;
import com.arpan.blog_app.payloads.PostDto;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto,Integer user_id, Integer categoryId);
    //update
    Post updatePost(PostDto postDto, Integer postId);
    //delete
    void deletePost(Integer postId);
    //get all posts
    List<Post> getAllPost();
    //get single post
    Post getPostById(Integer postId);
    //get all posts by category
    List<Post> getAllPostsByCategory(Integer categoryId);
    //get all posts form a user
    List<Post> getAllPostByUser(Integer user_id);
    //search posts
    List<Post> searchPosts(String keyword);
}
