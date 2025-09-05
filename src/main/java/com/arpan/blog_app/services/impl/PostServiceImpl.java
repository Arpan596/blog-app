package com.arpan.blog_app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.arpan.blog_app.entities.Category;
import com.arpan.blog_app.entities.Post;
import com.arpan.blog_app.entities.User;
import com.arpan.blog_app.exceptions.ResourceNotFoundException;
import com.arpan.blog_app.payloads.PostDto;
import com.arpan.blog_app.payloads.Postresponse;
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
        // post.setAddedDateTime(LocalDateTime.now());
        post.setUser(user);
        post.setCategory(category);

        Post newpPost= this.postRepo.save(post);

        return this.modelMapper.map(newpPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "Post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedpost = this.postRepo.save(post);

        PostDto postdto = this.modelMapper.map(updatedpost, PostDto.class);
        return postdto;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
        this.postRepo.delete(post);
    }

    @Override
    public Postresponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());
        Page<Post> pagepost = this.postRepo.findAll(p);

        List<Post> posts = pagepost.getContent();

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        Postresponse postresponse = new Postresponse();
        postresponse.setContent(postDtos);
        postresponse.setPageNumber(pagepost.getNumber());
        postresponse.setPageSize(pagepost.getSize());
        postresponse.setTotalElements(pagepost.getTotalElements());
        postresponse.setLastPage(pagepost.isLast());

        return postresponse;

    }

    @Override
    public PostDto getPostById(Integer postId) {
       Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId",postId));
       PostDto postDtos = this.modelMapper.map(post, PostDto.class);
       return postDtos;
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        
        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUser(Integer user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User", "user_id", user_id));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPosts'");
    }

}
