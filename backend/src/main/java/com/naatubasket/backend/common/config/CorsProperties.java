package com.naatubasket.backend.common.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.cors")
public class CorsProperties {

    /**
     * Browser origins allowed to call the API. Configured via
     * CORS_ALLOWED_ORIGINS as a comma separated list.
     */
    private List<String> allowedOrigins = List.of();

}
