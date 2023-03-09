package com.sdv.kit.blogserver.security;

import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest(classes = { RsaKeyGenerator.class })
class RsaKeyGeneratorTests {

    private final RsaKeyGenerator rsaKeyGenerator;

    @SneakyThrows
    @Test
    void generateRsaTest() {
        RSAKey rsaKey = rsaKeyGenerator.generateRsa();

        assertNotNull(rsaKey);
        assertNotNull(rsaKey.toPublicJWK());
        assertNotNull(rsaKey.toPrivateKey());
    }
}