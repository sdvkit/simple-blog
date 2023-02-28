package com.sdv.kit.blogserver.service.impl;

import com.sdv.kit.blogserver.dto.PostDto;
import com.sdv.kit.blogserver.mapper.PostMapper;
import com.sdv.kit.blogserver.repository.PostRepository;
import com.sdv.kit.blogserver.service.PostService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Cacheable("posts")
    @Override
    public List<PostDto> findAll() {
        return postRepository.findAll().stream()
                .map(postMapper::toDto)
                .toList();
    }

    @Cacheable("posts")
    @Override
    public List<PostDto> findAllByUser(String username) {
        return postRepository.findAllByUser(username).stream()
                .map(postMapper::toDto)
                .toList();
    }
}
