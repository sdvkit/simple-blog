package com.sdv.kit.blogserver.service.impl;

import com.sdv.kit.blogserver.dto.UserRegistrationDto;
import com.sdv.kit.blogserver.mapper.UserRegistrationMapper;
import com.sdv.kit.blogserver.model.User;
import com.sdv.kit.blogserver.repository.UserRepository;
import com.sdv.kit.blogserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Cacheable("users")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> {
                    final String message = String.format("Found user with username '%s': %s", username, user);
                    log.info(message);
                    return new com.sdv.kit.blogserver.security.UserDetails(user);
                })
                .orElseThrow(() -> {
                    final String message = String.format("User with username '%s' not found", username);
                    log.error(message);
                    return new UsernameNotFoundException(message);
                });
    }

    @Cacheable("users")
    @Transactional
    @Override
    public void registerUser(UserRegistrationDto userRegistrationDto) {
        userRepository.findByUsernameOrEmail(userRegistrationDto.username(), userRegistrationDto.email())
                .ifPresent(user -> {
                    final String message = String.format("Username '%s' or Email '%s' is already exists", user.getUsername(), user.getPassword());
                    log.error(message);
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
                });

        final UserRegistrationMapper mapper = Mappers.getMapper(UserRegistrationMapper.class);
        final User user = mapper.toEntity(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        log.info(String.format("User %s has been successfully saved to DB", user));
    }
}
