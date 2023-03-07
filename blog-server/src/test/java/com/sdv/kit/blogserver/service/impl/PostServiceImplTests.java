package com.sdv.kit.blogserver.service.impl;

import com.sdv.kit.blogserver.dto.PostDto;
import com.sdv.kit.blogserver.mapper.PostMapper;
import com.sdv.kit.blogserver.model.Post;
import com.sdv.kit.blogserver.model.User;
import com.sdv.kit.blogserver.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class PostServiceImplTests {

    private final PostServiceImpl postService;

    private final PasswordEncoder passwordEncoder;

    @MockBean
    private final PostRepository postRepository;

    private final List<Post> posts = new ArrayList<>();

    private final PostMapper mapper = Mappers.getMapper(PostMapper.class);

    @BeforeEach
    void setUpResources() {
        User user = User.builder()
                .id(1L)
                .username("Mikola")
                .password(passwordEncoder.encode("roottoor"))
                .email("delopata@gmail.com")
                .avatarUrl("/url/to/avatar")
                .build();

        posts.addAll(List.of(
                new Post(1L, "Birthday post 1", "Some description 1", LocalDateTime.of(2023, Month.FEBRUARY, 25, 14, 20), user),
                new Post(2L, "Birthday post 2", "Some description 2", LocalDateTime.of(2023, Month.FEBRUARY, 26, 15, 30), user),
                new Post(3L, "Birthday post 3", "Some description 3", LocalDateTime.of(2023, Month.FEBRUARY, 27, 16, 40), user)
        ));

        when(postRepository.findAll()).thenReturn(posts);
        when(postRepository.findAllByUser(anyString())).thenReturn(posts);
    }

    List<Post> mapDtosToEntities(List<PostDto> postDtos) {
        return postDtos.stream()
                .map(mapper::toEntity)
                .toList();
    }

    @Test
    void testFindAll() {
        List<Post> actualPosts = mapDtosToEntities(postService.findAll());

        for (int i = 0; i < posts.size(); i++) {
            assertEquals(posts.get(i).getId(), actualPosts.get(i).getId());
        }
    }

    @Test
    void testFindAllByUser() {
        List<Post> actualPosts = mapDtosToEntities(postService.findAllByUser("Mikola"));

        for (int i = 0; i < posts.size(); i++) {
            assertEquals(posts.get(i).getId(), actualPosts.get(i).getId());
        }
    }
}
