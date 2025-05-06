package com.arpan.blog_app.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int user_id;

    @NotEmpty
    @Size(min = 3, max = 20, message = "Username must be min of 3 characters")
    private String name;

    @Email(message = "Email address is not valid")
    private String email;
    
    @NotEmpty
    @Size(min = 4, max = 10, message = "password must be minimum of 4 charecters and 10 chars!!")
    private String password;
    
    @NotEmpty
    private String about;

}
