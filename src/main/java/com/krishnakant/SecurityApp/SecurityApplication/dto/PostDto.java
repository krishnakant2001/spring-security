package com.krishnakant.SecurityApp.SecurityApplication.dto;

import com.krishnakant.SecurityApp.SecurityApplication.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private UserDto author;
}
