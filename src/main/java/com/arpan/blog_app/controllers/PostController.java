package com.arpan.blog_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.blog_app.payloads.ApiResponse;
import com.arpan.blog_app.payloads.PostDto;
import com.arpan.blog_app.payloads.Postresponse;
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

    // get by user

    @GetMapping("/user/{user_id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer user_id){
        List<PostDto> posts = this.postService.getAllPostByUser(user_id);

        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // get by category
     @GetMapping("/category/{categoryId}/posts")
     public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts = this.postService.getAllPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
     }

     // get all post
     @GetMapping("/posts")
     public ResponseEntity<Postresponse> getAllPost(
        @RequestParam(value="pageNumber", defaultValue = "1", required = false) Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
        @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy    
        ){
        Postresponse postresponse = this.postService.getAllPost(pageNumber, pageSize,sortBy);
        return new ResponseEntity<Postresponse>(postresponse, HttpStatus.OK);
     }

     //get single post
     @GetMapping("/posts/{postId}")
     public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postdto = this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postdto, HttpStatus.OK);
     }

     //delete single post by id
     @DeleteMapping("/posts/{postId}")
     public ApiResponse ApiResponse(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted", true);
     }

     // update post
     @PutMapping("/posts/{postId}")
     public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto updatedpost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedpost, HttpStatus.OK);

         }

}
