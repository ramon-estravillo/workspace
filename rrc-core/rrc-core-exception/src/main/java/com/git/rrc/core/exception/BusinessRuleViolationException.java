package com.git.rrc.core.exception;

import com.git.rrc.core.exception.constant.ErrorCode;
import com.git.rrc.core.exception.constant.HttpStatus;
import com.git.rrc.core.exception.dto.Error;
import lombok.Getter;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

/**
 * Exception thrown when a high-level business or domain rule is violated.
 * <p>
 * Typically used to represent logical conditions in the system that prevent an operation
 * from continuing, such as trying to update a finalized record or performing an invalid transition.
 * </p>
 *
 * <p>Always mapped to {@code 400 Bad Request} unless otherwise specified.</p>
 *
 * <p>This exception supports attaching a list of {@link Error} details for granular context.</p>
 *
 * @see ApplicationException
 * @see ErrorCode
 * @see HttpStatus
 *
 * @author ramon
 * @version  1.0.0
 * @since 2025-07-19
 */
public class BusinessRuleViolationException extends ApplicationException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * A list of field-level or contextual errors describing what rule(s) were violated.
     */
    @Getter
    private final List<Error> errors;

    /**
     * Constructs with a default error code and HTTP status, and an optional message.
     *
     * @param message the exception message
     */
    public BusinessRuleViolationException(String message) {
        super(ErrorCode.resolve(-1), HttpStatus.BAD_REQUEST, message, null);
        this.errors = Collections.emptyList();
    }

    /**
     * Constructs with a message and a list of validation or business errors.
     *
     * @param message the exception message
     * @param errors list of individual error details
     */
    public BusinessRuleViolationException(String message, List<Error> errors) {
        super(ErrorCode.resolve(-1), HttpStatus.BAD_REQUEST, message, null);
        this.errors = errors;
    }

    /**
     * Constructs with a message and a root cause.
     *
     * @param message the exception message
     * @param cause the underlying cause
     */
    public BusinessRuleViolationException(String message, Throwable cause) {
        super(ErrorCode.resolve(-1), HttpStatus.BAD_REQUEST, message, cause);
        this.errors = Collections.emptyList();
    }

    /**
     * Constructs with message, cause, and list of errors.
     *
     * @param message the exception message
     * @param cause the underlying cause
     * @param errors list of error details
     */
    public BusinessRuleViolationException(String message, Throwable cause, List<Error> errors) {
        super(ErrorCode.resolve(-1), HttpStatus.BAD_REQUEST, message, cause);
        this.errors = errors;
    }

    /**
     * Constructs with custom {@link ErrorCode}, message, cause, and error list.
     *
     * @param code the custom error code
     * @param message the exception message
     * @param errors list of error details
     * @param cause the root cause
     */
    public BusinessRuleViolationException(ErrorCode code, String message, List<Error> errors, Throwable cause) {
        this(code, HttpStatus.BAD_REQUEST, message, errors, cause);
    }

    /**
     * Constructs with custom {@link ErrorCode} and error list.
     *
     * @param code the error code
     * @param errors list of detailed errors
     */
    public BusinessRuleViolationException(ErrorCode code, List<Error> errors) {
        super(code, HttpStatus.BAD_REQUEST, null);
        this.errors = errors;
    }

    /**
     * Constructs with custom error code, HTTP status, and error list.
     *
     * @param code the error code
     * @param status HTTP status
     * @param errors list of errors
     */
    public BusinessRuleViolationException(ErrorCode code, HttpStatus status, List<Error> errors) {
        super(code, status, null);
        this.errors = errors;
    }

    /**
     * Full constructor with all parameters.
     *
     * @param code the error code
     * @param status HTTP status
     * @param message custom message
     * @param errors list of error details
     * @param cause root exception cause
     */
    public BusinessRuleViolationException(ErrorCode code, HttpStatus status, String message, List<Error> errors, Throwable cause) {
        super(code, status, message, cause);
        this.errors = errors;
    }

    /**
     * Constructs using raw integer error code.
     *
     * @param code numeric error code
     * @param errors list of detailed errors
     */
    public BusinessRuleViolationException(int code, List<Error> errors) {
        super(code, HttpStatus.BAD_REQUEST.value(), null);
        this.errors = errors;
    }

    /**
     * Constructs using raw integer error code and HTTP status.
     *
     * @param code numeric error code
     * @param status numeric HTTP status
     * @param errors list of errors
     */
    public BusinessRuleViolationException(int code, int status, List<Error> errors) {
        super(code, status, null);
        this.errors = errors;
    }

    /**
     * Constructs using raw integer error code and message.
     *
     * @param code error code
     * @param message exception message
     * @param errors list of errors
     */
    public BusinessRuleViolationException(int code, String message, List<Error> errors) {
        super(code, HttpStatus.BAD_REQUEST.value(), message, null);
        this.errors = errors;
    }

    /**
     * Constructs using code, cause, and error list.
     *
     * @param code error code
     * @param cause root cause
     * @param errors list of error details
     */
    public BusinessRuleViolationException(int code, Throwable cause, List<Error> errors) {
        super(code, HttpStatus.BAD_REQUEST.value(), cause);
        this.errors = errors;
    }
}