package com.arpan.blog_app.services.impl;

import java.sql.Date;
import java.util.List;
import com.arpan.blog_app.entities.Category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpan.blog_app.entities.Post;
import com.arpan.blog_app.entities.User;
import com.arpan.blog_app.exceptions.ResourceNotFoundException;
import com.arpan.blog_app.payloads.PostDto;
import com.arpan.blog_app.repositories.CategoryRepo;
import com.arpan.blog_app.repositories.PostRepo;
import com.arpan.blog_app.repositories.UserRepo;
import com.arpan.blog_app.services.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer user_id, Integer categoryId) {

        User user = this.userRepo.findById(user_id)
        .orElseThrow(()->new ResourceNotFoundException("User","user_id", user_id));
        
        Category category = this.categoryRepo.findById(categoryId)
        .orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setAddedDate(new Date(0));
        post.setUser(user);
        post.setCategory(category);

        Post newpPost= this.postRepo.save(post);

        return this.modelMapper.map(newpPost, PostDto.class);
    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
    }

    @Override
    public void deletePost(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    @Override
    public List<Post> getAllPost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPost'");
    }

    @Override
    public Post getPostById(Integer postId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostById'");
    }

    @Override
    public List<Post> getAllPostsByCategory(Integer categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPostsByCategory'");
    }

    @Override
    public List<Post> getAllPostByUser(Integer user_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPostByUser'");
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}
