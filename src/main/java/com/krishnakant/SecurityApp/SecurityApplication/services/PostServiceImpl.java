package com.krishnakant.SecurityApp.SecurityApplication.services;

import com.krishnakant.SecurityApp.SecurityApplication.dto.PostDto;
import com.krishnakant.SecurityApp.SecurityApplication.entities.PostEntity;
import com.krishnakant.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.krishnakant.SecurityApp.SecurityApplication.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        PostEntity newPostEntity = postRepository.save(postEntity);
        return modelMapper.map(newPostEntity, PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id " + postId));

        return modelMapper.map(postEntity, PostDto.class);
    }
}
