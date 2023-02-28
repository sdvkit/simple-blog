package com.sdv.kit.blogserver.controller;

import com.sdv.kit.blogserver.dto.UserLoginDto;
import com.sdv.kit.blogserver.dto.UserRegistrationDto;
import com.sdv.kit.blogserver.service.TokenService;
import com.sdv.kit.blogserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity getToken(@RequestBody UserLoginDto userLoginDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.username(), userLoginDto.password()));
        return ResponseEntity.ok()
                .body(tokenService.generateToken(authentication));
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.registerUser(userRegistrationDto);
        return ResponseEntity.ok().build();
    }
}
