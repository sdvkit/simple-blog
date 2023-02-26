package com.sdv.kit.blogserver.controller;

import com.sdv.kit.blogserver.dto.PostDto;
import com.sdv.kit.blogserver.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostsController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(required = false) String username) {
        final List<PostDto> posts = (username == null)
                ? postService.findAll() : postService.findAllByUser(username);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(posts);
    }
}
