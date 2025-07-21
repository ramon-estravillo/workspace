package com.git.rrc.core.exception;

import com.git.rrc.core.exception.constant.ErrorCode;
import com.git.rrc.core.exception.constant.HttpStatus;
import lombok.Getter;

import java.io.Serial;
import java.util.Objects;

/**
 * Custom base exception for RRC applications to provide structured error handling.
 * Supports attaching both an {@link ErrorCode} and an {@link HttpStatus}.
 *
 * <p>This class offers multiple constructors to accommodate use cases with:
 * <ul>
 *     <li>Message and/or cause</li>
 *     <li>ErrorCode only</li>
 *     <li>ErrorCode and HttpStatus</li>
 *     <li>Raw integer codes</li>
 * </ul>
 * <p>Use {@link #isPresentErrorCode()} and {@link #isPresentHttpStatus()} to safely check for non-null values.
 *
 * @author ramon-estravillo
 * @version  1.0.0
 * @since 2025-07-19
 */
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The error code representing the logical/business error.
     */
    @Getter
    private final ErrorCode errorCode;

    /**
     * The HTTP status representing the appropriate REST-level error status.
     */
    @Getter
    private final HttpStatus httpStatus;

    /**
     * Constructs exception with a message.
     * @param message detailed message
     */
    public ApplicationException(String message) {
        super(message);
        this.errorCode  = null;
        this.httpStatus = null;
    }

    /**
     * Constructs exception with a cause.
     * @param cause the underlying cause
     */
    public ApplicationException(Throwable cause) {
        super(cause);
        this.errorCode  = null;
        this.httpStatus = null;
    }

    /**
     * Constructs exception with a message and cause.
     * @param message detailed message
     * @param cause underlying cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode  = null;
        this.httpStatus = null;
    }

    /**
     * Constructs exception using an {@link ErrorCode}.
     * @param code application-specific error code
     */
    public ApplicationException(ErrorCode code) {
        super(code.getReasonPhrase());
        this.errorCode = code;
        this.httpStatus = null;
    }

    /**
     * Constructs exception using an {@link ErrorCode} and a cause.
     * @param code error code
     * @param cause root cause
     */
    public ApplicationException(ErrorCode code, Throwable cause) {
        super(code.getReasonPhrase(), cause);
        this.errorCode = code;
        this.httpStatus = null;
    }

    /**
     * Constructs exception with custom message, {@link ErrorCode}, and cause.
     * @param code error code
     * @param message custom message
     * @param cause cause
     */
    public ApplicationException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = code;
        this.httpStatus = null;
    }

    /**
     * Constructs exception with {@link ErrorCode}, {@link HttpStatus}, and cause.
     * @param code error code
     * @param status HTTP status
     * @param cause root cause
     */
    public ApplicationException(ErrorCode code, HttpStatus status, Throwable cause) {
        super(code.getReasonPhrase(), cause);
        this.errorCode  = code;
        this.httpStatus = status;
    }

    /**
     * Constructs exception with {@link ErrorCode}, {@link HttpStatus}, message, and cause.
     * @param code error code
     * @param status HTTP status
     * @param message custom message
     * @param cause root cause
     */
    public ApplicationException(ErrorCode code, HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.errorCode  = code;
        this.httpStatus = status;
    }

    /**
     * Constructs exception from raw integer error code.
     * @param code integer error code
     * @param message message to override
     * @param cause root cause
     */
    public ApplicationException(int code, String message, Throwable cause) {
        super(message, cause);
        this.errorCode  = ErrorCode.resolve(code);
        this.httpStatus = null;
    }

    /**
     * Constructs exception from raw integer error code and cause.
     * @param code integer error code
     * @param cause root cause
     */
    public ApplicationException(int code, Throwable cause) {
        super(Objects.isNull(ErrorCode.resolve(code)) ? String.valueOf(code) : ErrorCode.valueOf(code).getReasonPhrase(), cause);
        this.errorCode  = ErrorCode.resolve(code);
        this.httpStatus = null;
    }

    /**
     * Constructs exception from raw error code and HTTP status.
     * @param code error code as integer
     * @param status HTTP status as integer
     * @param cause root cause
     */
    public ApplicationException(int code, int status, Throwable cause) {
        super(Objects.isNull(ErrorCode.resolve(code)) ? String.valueOf(code) : ErrorCode.valueOf(code).getReasonPhrase(), cause);
        this.errorCode  = ErrorCode.resolve(code);
        this.httpStatus = HttpStatus.resolve(status);
    }

    /**
     * Constructs exception with raw error code, HTTP status, custom message, and cause.
     * @param code error code
     * @param status HTTP status
     * @param message custom message
     * @param cause root cause
     */
    public ApplicationException(int code, int status, String message, Throwable cause) {
        super(message, cause);
        this.errorCode  = ErrorCode.resolve(code);
        this.httpStatus = HttpStatus.resolve(status);
    }

    /**
     * @return {@code true} if the {@link ErrorCode} is not null
     */
    public boolean isPresentErrorCode() {
        return Objects.nonNull(errorCode);
    }

    /**
     * @return {@code true} if the {@link HttpStatus} is not null
     */
    public boolean isPresentHttpStatus() {
        return Objects.nonNull(httpStatus);
    }

}