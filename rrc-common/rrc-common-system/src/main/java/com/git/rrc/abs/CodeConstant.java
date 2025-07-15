package com.git.rrc.abs;

/**
 * Represents a standardized error or status code constant with an associated descriptive message.
 * <p>
 * This interface is typically implemented by enums that define application-specific error codes,
 * HTTP status codes, or validation messages. It provides methods to retrieve both the code identifier
 * and a human-readable reason phrase explaining the code.
 * </p>
 */
public interface CodeConstant {

    /**
     * Returns the numeric value associated with this error code.
     *
     * @return the integer value representing the error code
     */
    int value();

    /**
     * Retrieves the unique error or status code associated with this constant.
     *
     * @return a {@code String} representing the code (e.g., "2000", "404", "ERR_INVALID_INPUT")
     */
    String getErrorCode();

    /**
     * Retrieves the human-readable message or reason phrase associated with this code.
     *
     * @return a {@code String} describing the purpose or meaning of the error or status code
     */
    String getReasonPhrase();
}
