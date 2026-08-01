package com.naatubasket.backend.common.exception;

/**
 * Thrown when a caller exceeds an allowed request rate, e.g. requesting a new
 * OTP before the resend cooldown has elapsed. Maps to HTTP 429.
 */
public class RateLimitException extends RuntimeException {

    public RateLimitException(String message) {
        super(message);
    }

}
