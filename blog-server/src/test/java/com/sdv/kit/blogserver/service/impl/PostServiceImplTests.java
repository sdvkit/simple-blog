package com.sdv.kit.blogserver.service.impl;

import com.sdv.kit.blogserver.dto.PostDto;
import com.sdv.kit.blogserver.mapper.PostMapper;
import com.sdv.kit.blogserver.model.Post;
import com.sdv.kit.blogserver.model.User;
import com.sdv.kit.blogserver.repository.PostRepository;
import com.sdv.kit.blogserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
@SpringBootTest
class PostServiceImplTests {

    private final PostServiceImpl postService;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final PostMapper mapper = Mappers.getMapper(PostMapper.class);

    private User expectedUser;

    private List<Post> posts;

    @BeforeEach
    void setUpResources() {
        userRepository.findByUsername("Mikola 1").ifPresent(user -> expectedUser = user);
        posts = postRepository.findAll();
    }

    List<Post> mapDtosToEntities(List<PostDto> postDtos) {
        return postDtos.stream()
                .map(mapper::toEntity)
                .toList();
    }

    @Test
    void testFindAll() {
        final List<Post> actualPosts = mapDtosToEntities(postService.findAll());

        for (int i = 0; i < posts.size(); i++) {
            assertEquals(posts.get(i).getTitle(), actualPosts.get(i).getTitle());
        }
    }

    @Test
    void testFindAllByUser() {
        final List<Post> actualPosts = mapDtosToEntities(postService.findAllByUser(expectedUser.getUsername()));
        final int expectedPostsCount = posts.stream().filter(post -> post.getUser()
                .getUsername()
                .equals(expectedUser.getUsername()))
                .toList()
                .size();

        assertEquals(expectedPostsCount, actualPosts.size());
    }
}
