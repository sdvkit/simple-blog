package com.sdv.kit.blogserver.service.impl;

import com.sdv.kit.blogserver.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    private final JwtEncoder jwtEncoder;

    @Override
    public String generateToken(Authentication authentication) {
        final Instant instantNow = Instant.now();
        final Instant instantExpiration = instantNow.plus(10, ChronoUnit.DAYS);

        final JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instantNow)
                .expiresAt(instantExpiration)
                .issuer("sdvkit")
                .subject(authentication.getName())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        log.info(String.format("JWT token has been generated %s", token));

        return token;
    }
}
