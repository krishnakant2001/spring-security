package com.krishnakant.SecurityApp.SecurityApplication.services;

import com.krishnakant.SecurityApp.SecurityApplication.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);
}
