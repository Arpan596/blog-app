package com.arpan.blog_app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int categoryId;
    private String categoryType;
    private String categoyDescription; 
}
