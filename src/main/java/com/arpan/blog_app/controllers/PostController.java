package com.arpan.blog_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.blog_app.payloads.PostDto;
import com.arpan.blog_app.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;
    //create
    @PostMapping("/user/{user_id}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
        @RequestBody PostDto postDto,
     @PathVariable Integer user_id,
     @PathVariable Integer categoryId){

        PostDto createPost=this.postService.createPost(postDto, user_id, categoryId);
        return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
        
    }
}
