package com.arpan.blog_app.services;

import java.util.List;

import com.arpan.blog_app.payloads.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer user_id);
    List<UserDto> getAllUser();
    UserDto getUserById(Integer user_id);
    void deleteUser(Integer user_id);

}
