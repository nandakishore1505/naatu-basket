package com.naatubasket.backend.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * Secret key used to sign JWTs.
     */
    private String secret;

    /**
     * Access token validity in milliseconds.
     */
    private long accessTokenExpiration;

    /**
     * Refresh token validity in milliseconds.
     */
    private long refreshTokenExpiration;

}