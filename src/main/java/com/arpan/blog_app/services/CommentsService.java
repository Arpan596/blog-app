package com.arpan.blog_app.services;

import com.arpan.blog_app.payloads.CommentsDto;

public interface CommentsService {

    
    // create
    CommentsDto createComment(CommentsDto commentsDto, Integer postId, Integer user_id);
    // update
    CommentsDto updatecComment(CommentsDto commentsDto, Integer commentId);
    // delete
    void deleteComment(Integer commentId);
}