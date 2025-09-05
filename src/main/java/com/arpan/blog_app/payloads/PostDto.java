package com.arpan.blog_app.payloads;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Integer postId;
    
    private String title;

    private String content;

    private String imageName="default.img";
    
    private LocalDateTime addedDateTime;

    private CategoryDto category;

    private UserDto user;


}
