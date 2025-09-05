package com.arpan.blog_app.services;

import java.util.List;

import com.arpan.blog_app.payloads.PostDto;
import com.arpan.blog_app.payloads.Postresponse;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto,Integer user_id, Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto, Integer postId);
    //delete
    void deletePost(Integer postId);
    //get all posts
    Postresponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
    //get single post
    PostDto getPostById(Integer postId);
    //get all posts by category
    List<PostDto> getAllPostsByCategory(Integer categoryId);
    //get all posts form a user
    List<PostDto> getAllPostByUser(Integer user_id);
    //search posts
    List<PostDto> searchPosts(String keyword);
}
