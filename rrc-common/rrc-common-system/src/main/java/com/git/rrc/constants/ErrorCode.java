package com.git.rrc.constants;

import com.git.rrc.abs.ReasonPhrase;
import org.springframework.lang.Nullable;

/**
 * Enumeration of standardized application error codes with associated integer values.
 *
 * <p>
 * Each {@code ErrorCode} represents a specific error or exception scenario encountered
 * during application execution. These codes are intended for use in API responses,
 * logging, and error handling.
 * </p>
 *
 * <p>
 * The enum implements {@link StatusCode}, allowing consistent access to:
 * <ul>
 *     <li>{@code value()} — the numeric error code</li>
 *     <li>{@code getErrorCode()} — the enum constant name</li>
 *     <li>{@code getReasonPhrase()} — a human-readable message via {@link ReasonPhrase}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Error codes are grouped by functional category:
 * <ul>
 *     <li><strong>General Errors</strong> (2xxx)</li>
 *     <li><strong>Validation &amp; Resource State Errors</strong> (21xx)</li>
 *     <li><strong>Authentication &amp; Authorization Errors</strong> (22xx)</li>
 *     <li><strong>Request Format &amp; Structure Errors</strong> (23xx)</li>
 *     <li><strong>Server &amp; Infrastructure Errors</strong> (24xx)</li>
 * </ul>
 * </p>
 *
 * <h3>Example usage:</h3>
 * <pre>{@code
 * ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;
 * String code = errorCode.getErrorCode();           // "RESOURCE_NOT_FOUND"
 * String message = errorCode.getReasonPhrase();     // e.g., "Resource does not exist."
 * int value = errorCode.value();                    // 2100
 * }</pre>
 *
 * @author Ramon
 * @version 1.0.0
 * @since 2025-07-15
 */
public enum ErrorCode implements StatusCode {
    // General Errors
    BAD_REQUEST(2000),
    OPERATION_NOT_SUPPORTED(2001),
    RATE_LIMIT_EXCEEDED(2002),
    REQUEST_CONFLICT(2003),
    DUPLICATE_RESOURCE(2004),

    // Validation & Resource State Errors
    RESOURCE_NOT_FOUND(2100),
    OPTIMISTIC_LOCK(2101),
    VALIDATION_FAILED(2102),
    MANDATORY_FIELDS_EMPTY(2103),
    VALUE_OUT_OF_RANGE(2104),
    CONSTRAINTS_VIOLATION(2105),
    OPERATION_NOT_ALLOWED(2106),
    INVALID_REFERENCE(2107),
    IMMUTABLE_FIELD_RESOURCE(2108),
    MISSING_DEPENDENT_RESOURCE(2109),

    // Authentication & Authorization Errors
    AUTHENTICATION_FAILED(2200),
    AUTHORIZATION_FAILED(2201),
    SESSION_EXPIRED(2202),
    INVALID_TOKEN(2203),
    ACCOUNT_LOCKED(2204),
    PERMISSION_DENIED(2205),

    // Request Format & Structure Errors
    REQUEST_PAYLOAD_MALFORMED(2300),
    MISSING_REQUEST_PARAMS(2301),
    INVALID_QUERY_PARAMS(2302),
    UNSUPPORTED_MEDIA_TYPE(2303),
    REQUEST_PAYLOAD_EXCEED_LIMIT(2304),
    INVALID_ENUM_VALUE_OPTION(2305),
    INVALID_DATETIME_NUMBER_FORMAT(2306),
    MISSING_PAGINATION_PARAMS(2307),
    FEATURE_NOT_AVAILABLE(2308),

    // Server & Infrastructure Errors
    INTERNAL_SERVER_ERROR(2400),
    SERVICE_UNAVAILABLE(2401),
    REQUEST_TIMEOUT(2402),
    DATABASE_ERROR(2403),
    DATA_ACCESS_ERROR(2404),
    CONFIGURATION_ERROR(2405),
    SERVICE_NOT_RESPONDING(2406),
    FILE_SYSTEM_ERROR(2407),
    UNEXPECTED_EXCEPTION(2408),
    RESOURCE_LOCK_ERROR(2409);

    private final int value;
    private static final ErrorCode[] VALUES = values();

    /**
     * Constructs an {@code ErrorCode} with the specified numeric value.
     *
     * @param value the unique integer identifier for the error
     */
    ErrorCode(int value) {
        this.value = value;
    }

    /**
     * Returns the numeric code associated with this error.
     *
     * @return the error code as an integer
     */
    @Override
    public int value() {
        return value;
    }

    /**
     * Resolves an {@code ErrorCode} for the given numeric value.
     *
     * @param value the numeric error code
     * @return the corresponding {@code ErrorCode}, or {@code null} if none matches
     */
    @Nullable
    public static ErrorCode resolve(int value) {
        for(ErrorCode code: VALUES) {
            if(code.value == value) {
                return code;
            }
        }
        return null;
    }

    /**
     * Returns the {@code ErrorCode} for the specified numeric value.
     *
     * @param value the numeric error code
     * @return the matching {@code ErrorCode}
     * @throws EnumConstantNotPresentException if no match is found
     */
    public static ErrorCode valueOf(int value) {
        ErrorCode code = resolve(value);
        if(code == null) {
            throw new EnumConstantNotPresentException(ErrorCode.class, String.valueOf(value));
        }
        return code;
    }
}
