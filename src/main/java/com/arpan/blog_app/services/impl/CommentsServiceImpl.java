package com.arpan.blog_app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpan.blog_app.entities.Comments;
import com.arpan.blog_app.entities.Post;
import com.arpan.blog_app.entities.User;
import com.arpan.blog_app.exceptions.ResourceNotFoundException;
import com.arpan.blog_app.payloads.CommentsDto;
import com.arpan.blog_app.repositories.CommentRepo;
import com.arpan.blog_app.repositories.PostRepo;
import com.arpan.blog_app.repositories.UserRepo;
import com.arpan.blog_app.services.CommentsService;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentsDto createComment(CommentsDto commentsDto, Integer postId, Integer user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("user", "userId", user_id));
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postId", postId));
        Comments comments = this.modelMapper.map(commentsDto, Comments.class);
        comments.setUser(user);
        comments.setPost(post);
        Comments newComment = this.commentRepo.save(comments);
        return this.modelMapper.map(newComment, CommentsDto.class);
        
    }

    @Override
    public CommentsDto updatecComment(CommentsDto commentsDto, Integer commentId) {
        Comments comments = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "commentId", commentId));
        comments.setContent(commentsDto.getContent());
        Comments upadtedComments = this.commentRepo.save(comments);
        return this.modelMapper.map(upadtedComments, CommentsDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comments comments = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "comment id", commentId));
        this.commentRepo.delete(comments);

    }
    
}
