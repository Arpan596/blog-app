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
import org.springframework.web.bind.annotation.RestController;

import com.arpan.blog_app.payloads.ApiResponse;
import com.arpan.blog_app.payloads.UserDto;
import com.arpan.blog_app.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
        
    }
    //create user
    @PutMapping("/{user_id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer user_id){

        UserDto updatedUser = this.userService.updateUser(userDto, user_id);
        return ResponseEntity.ok(updatedUser);
    }
    // delete user
    @DeleteMapping("/{user_id}")
    public ApiResponse deleteUser(@PathVariable Integer user_id){
        this.userService.deleteUser(user_id);
        return new ApiResponse("User deleted successfully", true);
    }
    //get all users details
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUser());
    }
    //get a single user detail  
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer user_id) {
    UserDto user = this.userService.getUserById(user_id);
    return ResponseEntity.ok(user);
    }

}