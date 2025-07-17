package com.git.rrc.core.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a field-level validation or input error.
 * Typically used as part of an {@link ErrorResponse} to describe individual problems
 * with request parameters or payload fields.
 *
 * <p>Example:
 * <pre>
 * {
 *   "field": "email",
 *   "message": "must be a valid email address",
 *   "rejectedValue": "invalid-email"
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** The name of the field that caused the error. */
    private String field;

    /** A human-readable message describing the validation error. */
    private String message;

    /** The rejected or invalid value that was submitted, if available. */
    private String rejectedValue;

    /**
     * Default constructor.
     */
    public Error() {
    }

    /**
     * Constructs an Error object with field name and rejected value.
     *
     * @param field         The field name that failed validation.
     * @param rejectedValue The value that was rejected (can be null).
     */
    public Error(String field, String rejectedValue) {
        this(field, String.format("must be a valid %s value", field), rejectedValue);
    }

    /**
     * Constructs an Error object with field name, message, and rejected value.
     *
     * @param field         The field name that failed validation.
     * @param message       The error message describing the issue.
     * @param rejectedValue The value that was rejected (can be null).
     */
    public Error(String field, String message, String rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }
}
