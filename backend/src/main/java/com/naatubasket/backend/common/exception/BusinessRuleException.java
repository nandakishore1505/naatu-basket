package com.naatubasket.backend.common.exception;

/**
 * Thrown when a request is syntactically valid but violates a domain rule,
 * e.g. a category being made its own parent. Maps to HTTP 400.
 */
public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String message) {
        super(message);
    }

}
