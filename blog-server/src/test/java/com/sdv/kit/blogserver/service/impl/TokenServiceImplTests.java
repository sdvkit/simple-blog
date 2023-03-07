package com.sdv.kit.blogserver.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TokenServiceImplTests {

    private TokenServiceImpl tokenService;

    @Mock
    private JwtEncoder jwtEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tokenService = new TokenServiceImpl(jwtEncoder);
    }

    @Test
    void generateToken_returnsValidJwtToken() {
        final Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "password");
        final Instant instantNow = Instant.now();
        final Instant instantExpiration = instantNow.plus(10, ChronoUnit.DAYS);

        final Jwt expectedJwt = Jwt.withTokenValue("test-jwt-token")
                .header("typ", "JWT")
                .claim("iss", "sdvkit")
                .claim("sub", "testuser")
                .issuedAt(instantNow)
                .expiresAt(instantExpiration)
                .build();

        when(jwtEncoder.encode(any())).thenReturn(expectedJwt);

        final String actualJwtToken = tokenService.generateToken(authentication);

        verify(jwtEncoder).encode(any());

        assertNotNull(actualJwtToken);
        assertEquals(expectedJwt.getTokenValue(), actualJwtToken);
    }
}
