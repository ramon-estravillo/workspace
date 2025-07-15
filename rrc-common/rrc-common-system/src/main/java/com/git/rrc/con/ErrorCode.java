package com.git.rrc.con;

import com.git.rrc.abs.CodeConstant;
import com.git.rrc.abs.ReasonPhrase;

public enum ErrorCode implements CodeConstant {
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

    ErrorCode(int value) {
        this.value = value;
    }

    @Override
    public String getErrorCode() {
        return name();
    }

    @Override
    public String getReasonPhrase() {
        return ReasonPhrase.valueOf(value);
    }

    public static ErrorCode resolve(int value) {
        for(ErrorCode code: VALUES) {
            if(code.value == value) {
                return code;
            }
        }
        return null;
    }

    public static ErrorCode valueOf(int value) {
        ErrorCode code = resolve(value);
        if(code == null) {
            throw new EnumConstantNotPresentException(ErrorCode.class, String.valueOf(value));
        }
        return code;
    }
}
