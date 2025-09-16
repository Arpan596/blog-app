package com.arpan.blog_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpan.blog_app.payloads.ApiResponse;
import com.arpan.blog_app.payloads.CommentsDto;
import com.arpan.blog_app.services.CommentsService;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentsService commentsService;

    @PostMapping("/post/{postId}/user/{user_id}/comments")
    public ResponseEntity<CommentsDto> createComment(@PathVariable Integer postId, Integer user_id, CommentsDto commentsDto){

        CommentsDto createdComment = this.commentsService.createComment(commentsDto, postId, user_id);
        return new ResponseEntity<CommentsDto>(createdComment, HttpStatus.CREATED);

    }

    @PutMapping("/comments{commentId}")
    public ResponseEntity<CommentsDto> updateComment(@PathVariable Integer commentId, CommentsDto commentsDto){
        CommentsDto updaComments = this.commentsService.updatecComment(commentsDto, commentId);
        return new ResponseEntity<CommentsDto>(updaComments, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentsService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully", true), HttpStatus.OK);
    }

}

    

