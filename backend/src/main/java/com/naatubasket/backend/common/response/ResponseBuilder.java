package com.naatubasket.backend.common.response;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static <T> ApiResponse<T> success(String message, T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> failure(String message) {

        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

}