package com.naatubasket.backend.common.exception;

/**
 * Thrown when supplied credentials (password, OTP or refresh token) are wrong,
 * expired or belong to a disabled account. Maps to HTTP 401.
 *
 * <p>Messages must stay deliberately vague so the endpoint cannot be used to
 * discover which phone numbers are registered.</p>
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }

}
