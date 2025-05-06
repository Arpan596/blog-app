package com.arpan.blog_app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpan.blog_app.exceptions.*;
import com.arpan.blog_app.entities.User;
import com.arpan.blog_app.payloads.UserDto;
import com.arpan.blog_app.repositories.UserRepo;
import com.arpan.blog_app.services.UserService;

@Service
public class UserServiceImple implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = (User) this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer user_id) {
        User user = this.userRepo.findById(user_id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id));
        //user.setId(userDto.getUser_id());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);

        return userDto1;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos =
        users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos; 
    }

    @Override
    public UserDto getUserById(Integer user_id) {

        User user = this.userRepo.findById(user_id)
        .orElseThrow(() -> new ResourceNotFoundException("User","Id",user_id));
        return this.userToDto(user);
    }

    @Override
    public void deleteUser(Integer user_id) {

        User user = this.userRepo.findById(user_id)
        .orElseThrow(()-> new ResourceNotFoundException("User","Id",user_id));
        this.userRepo.delete(user);
        
    }

    private User dtoToUser(UserDto userDto) {
        User user = new ModelMapper().map(userDto, User.class);

        // user.setId(userDto.getUser_id());
        // user.setName(userDto.getName());
        // user.setEmail(userDto.getEmail());
        // user.setPassword(userDto.getPassword());
        // user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = new ModelMapper().map(user, UserDto.class);
        // userDto.setUser_id(user.getId());
        // userDto.setEmail(user.getEmail());
        // userDto.setName(user.getName());
        // userDto.setPassword(user.getPassword());
        // userDto.setAbout(user.getAbout());
        return userDto;
    }

}
