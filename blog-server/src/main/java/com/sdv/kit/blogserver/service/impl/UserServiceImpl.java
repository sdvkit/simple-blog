package com.sdv.kit.blogserver.service.impl;

import com.sdv.kit.blogserver.dto.UserDto;
import com.sdv.kit.blogserver.dto.UserRegistrationDto;
import com.sdv.kit.blogserver.mapper.UserMapper;
import com.sdv.kit.blogserver.model.User;
import com.sdv.kit.blogserver.repository.UserRepository;
import com.sdv.kit.blogserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(com.sdv.kit.blogserver.security.UserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with this username not found"));
    }

    @Cacheable("users")
    @Override
    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException("User with this username not found"));
    }

    @Cacheable("users")
    @Transactional
    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        userRepository.findByUsernameOrEmail(userRegistrationDto.username(), userRegistrationDto.email())
                .ifPresent(user -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this username or email already exists"); });

        final User user = userMapper.toEntity(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Cacheable("users")
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }
}
