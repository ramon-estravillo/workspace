package com.git.rrc.core.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Standardized error response object based on the W3C Problem Details specification (RFC 7807),
 * enhanced with additional fields for application-level troubleshooting.
 *
 * <p>Used for conveying structured error information in RESTful APIs, including HTTP status,
 * error code, details, and field-level validation errors.</p>
 *
 * <p>Example usage:
 * <pre>
 * {
 *   "type": "https://example.com/errors/validation",
 *   "title": "Validation Error",
 *   "status": 400,
 *   "detail": "Invalid input data",
 *   "instance": "/api/users",
 *   "errorCode": "USR_001",
 *   "timestamp": "2025-07-08T12:00:00Z",
 *   "errors": [
 *     { "field": "email", "message": "must be a valid email", "rejectedValue": "invalid" }
 *   ]
 * }
 * </pre>
 * </p>
 *
 * @author Ramon
 * @version 1.0.0
 * @since 2025-07-08
 */
@Builder
@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** A URI identifier for the type of error (e.g., a documentation link). */
    private String type;

    /** A short, human-readable summary of the error. */
    private String title;

    /** The HTTP status code (e.g., 400, 404, 500). */
    private int status;

    /** A detailed explanation of the error. */
    private String detail;

    /** The URI of the request that caused the error. */
    private String instance;

    /** An application-specific error code to help identify the error. */
    private String errorCode;

    /** The timestamp when the error occurred, in UTC. */
    @Builder.Default
    private Instant timestamp = Instant.now();

    /** A list of individual field-level or validation errors, if applicable. */
    @Builder.Default
    private List<Error> errors = new ArrayList<>();

    /**
     * Default constructor that initializes the timestamp and error list.
     */
    public ErrorResponse() {
        this.timestamp = Instant.now();
        this.errors = new ArrayList<>();
    }

    /**
     * Constructs a new ErrorResponse with the provided values.
     *
     * @param type       A URI reference that identifies the problem type.
     * @param title      A short, human-readable summary of the problem.
     * @param status     The HTTP status code.
     * @param detail     A detailed explanation specific to this occurrence.
     * @param instance   A URI reference that identifies the specific occurrence.
     * @param errorCode  An application-defined error code for internal use.
     */
    public ErrorResponse(String type, String title, int status, String detail, String instance, String errorCode) {
        this();
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.instance = instance;
        this.errorCode = errorCode;
    }
}