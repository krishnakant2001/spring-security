package com.krishnakant.SecurityApp.SecurityApplication.utils;

import com.krishnakant.SecurityApp.SecurityApplication.dto.PostDto;
import com.krishnakant.SecurityApp.SecurityApplication.entities.User;
import com.krishnakant.SecurityApp.SecurityApplication.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;

    public boolean isOwnerOfPost(Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDto post = postService.getPostById(id);
        return post.getAuthor().getId().equals(user.getId());
    }
}
