package com.sdv.kit.blogserver.service;

import com.sdv.kit.blogserver.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAll();

    List<PostDto> findAllByUser(String username);
}
