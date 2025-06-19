package com.arpan.blog_app.payloads;

import java.sql.Date;
import java.util.Locale.Category;

import com.arpan.blog_app.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private String title;

    private String content;

    private String imageName="default.img";
    
    private Date addedDate;

    private Category category;

    private User user;


}
