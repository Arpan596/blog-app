package com.arpan.blog_app.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {


    private int categoryId;
    @NotBlank
    @Size(min = 3)
    private String categoryType;
    @NotBlank
    @Size(min = 10)
    private String categoryDescription; 
}
