package com.sdv.kit.blogserver.service.impl;

import com.sdv.kit.blogserver.dto.PostDto;
import com.sdv.kit.blogserver.repository.PostRepository;
import com.sdv.kit.blogserver.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Cacheable("posts")
    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(post -> {
                    PostDto postDto = new PostDto(post.getId(), post.getName(), post.getDescription(), post.getCreatedAt());
                    log.info(String.format("Post dto has been generated from 'findAll' method: %s", postDto));
                    return postDto;
                })
                .toList();
    }

    @Cacheable("posts")
    @Override
    public List<PostDto> findAllByUser(String username) {
        return postRepository.findAllByUser(username).stream()
                .map(post -> {
                    PostDto postDto = new PostDto(post.getId(), post.getName(), post.getDescription(), post.getCreatedAt());
                    log.info(String.format("Post dto has been generated from 'findAllByUser' method: %s", postDto));
                    return postDto;
                })
                .toList();
    }
}
