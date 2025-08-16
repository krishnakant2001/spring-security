package com.krishnakant.SecurityApp.SecurityApplication.controllers;

import com.krishnakant.SecurityApp.SecurityApplication.dto.PostDto;
import com.krishnakant.SecurityApp.SecurityApplication.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN') OR hasAnyAuthority('POST_VIEW')")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost){
        return postService.createNewPost(inputPost);
    }
}
