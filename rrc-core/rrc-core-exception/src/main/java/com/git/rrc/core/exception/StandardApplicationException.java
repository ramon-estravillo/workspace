package com.git.rrc.core.exception;

import com.git.rrc.core.exception.constant.ErrorCode;
import com.git.rrc.core.exception.constant.HttpStatus;
import lombok.Getter;

/**
 * {@code StandardApplicationException} is a custom runtime exception
 * designed to provide standardized error handling across the application.
 * <p>
 * It encapsulates both an {@link ErrorCode} and an {@link HttpStatus}
 * to represent both business-level and HTTP-level error semantics.
 * </p>
 *
 * <p>This exception is typically thrown when a domain or system-level error
 * needs to be translated into a structured response format.</p>
 *
 * @see ErrorCode
 * @see HttpStatus
 */
@Getter
public class StandardApplicationException extends RuntimeException {

    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    /**
     * Constructs a default exception with {@code INTERNAL_SERVER_ERROR}
     * for both error code and HTTP status.
     */
    public StandardApplicationException() {
        this(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs an exception from a numeric error code.
     * Uses {@code INTERNAL_SERVER_ERROR} as the default HTTP status.
     *
     * @param code the numeric error code
     */
    public StandardApplicationException(int code) {
        this(ErrorCode.valueOf(code));
    }

    /**
     * Constructs an exception from numeric error and HTTP status codes.
     *
     * @param code   the error code
     * @param status the HTTP status code
     */
    public StandardApplicationException(int code, int status) {
        this(ErrorCode.valueOf(code), HttpStatus.valueOf(status));
    }

    /**
     * Constructs an exception from numeric error and HTTP status codes,
     * including a root cause.
     *
     * @param code   the error code
     * @param status the HTTP status code
     * @param cause  the root exception
     */
    public StandardApplicationException(int code, int status, Throwable cause) {
        this(ErrorCode.valueOf(code), HttpStatus.valueOf(status), cause);
    }

    /**
     * Constructs an exception with a specific {@link ErrorCode}.
     * Uses {@code INTERNAL_SERVER_ERROR} as the default HTTP status.
     *
     * @param code the custom error code
     */
    public StandardApplicationException(ErrorCode code) {
        this(code, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Constructs an exception with a specific {@link ErrorCode} and {@link HttpStatus}.
     *
     * @param code   the custom error code
     * @param status the HTTP status
     */
    public StandardApplicationException(ErrorCode code, HttpStatus status) {
        super(code.getReasonPhrase());
        this.errorCode = code;
        this.httpStatus = status;
    }

    /**
     * Constructs an exception with an error code, HTTP status, and a root cause.
     *
     * @param code   the custom error code
     * @param status the HTTP status
     * @param cause  the underlying exception
     */
    public StandardApplicationException(ErrorCode code, HttpStatus status, Throwable cause) {
        super(code.getReasonPhrase(), cause);
        this.errorCode = code;
        this.httpStatus = status;
    }

}
