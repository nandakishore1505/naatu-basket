package com.naatubasket.backend.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Applies a single {@code /api/v1} prefix to every {@link RestController} so
 * individual controllers declare only their own resource path. This keeps the
 * codebase aligned with the versioned API described in the architecture docs
 * and means a future v2 only needs a change here.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String API_PREFIX = "/api/v1";

    private static final String BASE_PACKAGE = "com.naatubasket.backend";

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        // Restricted to our own package so springdoc's controllers keep serving
        // /v3/api-docs and /swagger-ui.html at their documented locations.
        configurer.addPathPrefix(
                API_PREFIX,
                handlerType -> handlerType.isAnnotationPresent(RestController.class)
                        && handlerType.getPackageName().startsWith(BASE_PACKAGE));
    }

}
