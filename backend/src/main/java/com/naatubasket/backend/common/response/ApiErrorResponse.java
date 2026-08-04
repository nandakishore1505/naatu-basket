package com.naatubasket.backend.common.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiErrorResponse {

    private boolean success;

    private int status;

    private String error;

    private String message;

    private String path;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

}