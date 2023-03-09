package com.sdv.kit.blogserver.mapper;

import com.sdv.kit.blogserver.dto.PostCreationDto;
import com.sdv.kit.blogserver.dto.PostDto;
import com.sdv.kit.blogserver.model.Post;
import com.sdv.kit.blogserver.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PostMapperTests {

    private final PostRepository postRepository;

    private final PostMapper mapper = Mappers.getMapper(PostMapper.class);

    private Post expectedPost;

    @BeforeEach
    void setUpResources() {
        postRepository.findById(1L).ifPresent(post -> expectedPost = post);
    }

    @Test
    void fromEntityToDtoTest() {
        PostDto actualPostDto = mapper.toDto(expectedPost);

        assertEquals(expectedPost.getId(),actualPostDto.id());
        assertEquals(expectedPost.getTitle(),actualPostDto.title());
        assertEquals(expectedPost.getDescription(),actualPostDto.description());
        assertEquals(expectedPost.getCreatedAt(),actualPostDto.createdAt());
    }

    @Test
    void fromDtoToEntityTest() {
        PostDto postDto = mapper.toDto(expectedPost);
        Post actualPost = mapper.toEntity(postDto);

        assertEquals(postDto.id(), actualPost.getId());
        assertEquals(postDto.title(), actualPost.getTitle());
        assertEquals(postDto.description(), actualPost.getDescription());
        assertEquals(postDto.createdAt(), actualPost.getCreatedAt());
    }

    @Test
    void fromEntityToCreationDtoTest() {
        PostCreationDto actualDto = mapper.toCreationDto(expectedPost);

        assertEquals(expectedPost.getTitle(),actualDto.title());
        assertEquals(expectedPost.getDescription(),actualDto.description());
    }

    @Test
    void fromCreationDtoToEntityTest() {
        PostCreationDto postCreationDto = mapper.toCreationDto(expectedPost);
        Post actualPost = mapper.toEntity(postCreationDto);

        assertEquals(postCreationDto.title(), actualPost.getTitle());
        assertEquals(postCreationDto.description(), actualPost.getDescription());
    }
}
