package com.sdv.kit.blogserver.service.impl;

import com.sdv.kit.blogserver.dto.UserDto;
import com.sdv.kit.blogserver.mapper.UserMapper;
import com.sdv.kit.blogserver.model.User;
import com.sdv.kit.blogserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles("test")
@SpringBootTest
class UserServiceImplTests {

    private final UserServiceImpl userService;

    private final UserRepository userRepository;

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    private List<User> users;

    private User expectedUser;

    @BeforeEach
    void setUpResources() {
        users = userRepository.findAll();
        userRepository.findByUsername("Mikola 1").ifPresent(user -> expectedUser = user);
    }

    List<User> mapDtosToEntities(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(mapper::toEntity)
                .toList();
    }

    @Test
    void loadUserByUsernameTest() {
        final UserDetails userDetails = userService.loadUserByUsername("Mikola 1");

        assertEquals(expectedUser.getUsername(), userDetails.getUsername());
        assertEquals(expectedUser.getPassword(), userDetails.getPassword());
    }

    @Test
    void findByUsernameTest() {
        final UserDto actualUser = userService.findByUsername("Mikola 1");

        assertEquals(expectedUser.getUsername(), actualUser.username());
        assertEquals(expectedUser.getEmail(), actualUser.email());
        assertEquals(expectedUser.getAvatarUrl(), actualUser.avatarUrl());
    }

    @Test
    void registerUserTest() {
        final User userToSave = User.builder()
                .posts(Collections.emptyList())
                .email("test-delopata@gmail.com")
                .avatarUrl("/avatar/url/test")
                .username("Mikola 1")
                .password("test1234")
                .build();

        assertThrows(ResponseStatusException.class, () -> userService.registerUser(mapper.toRegistrationDto(userToSave)));

        userToSave.setUsername("Mikola test");

        assertDoesNotThrow(() -> userService.registerUser(mapper.toRegistrationDto(userToSave)));
    }

    @Test
    void findAllTest() {
        final List<User> actualUsers = mapDtosToEntities(userService.findAll());

        for (int i = 0; i < users.size(); i++) {
            assertEquals(users.get(i).getUsername(), actualUsers.get(i).getUsername());
            assertEquals(users.get(i).getEmail(), actualUsers.get(i).getEmail());
            assertEquals(users.get(i).getAvatarUrl(), actualUsers.get(i).getAvatarUrl());
        }
    }
}