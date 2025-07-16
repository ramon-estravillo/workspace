package com.git.rrc.constants;

import com.git.rrc.abs.ReasonPhrase;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.Nullable;

/**
 * Enumeration of standard HTTP status codes with their associated integer values.
 *
 * <p>
 * Each {@code HttpStatus} represents a specific HTTP response status as defined in
 * the HTTP/1.1 specification and extended status definitions. This enum categorizes status
 * codes based on their numeric range and provides integration with the {@link ReasonPhrase}
 * utility for human-readable descriptions.
 * </p>
 *
 * <p>
 * This enum implements {@link StatusCode}, allowing consistent access to:
 * <ul>
 *     <li>{@code value()} — the numeric status code</li>
 *     <li>{@code getErrorCode()} — the enum constant name</li>
 *     <li>{@code getReasonPhrase()} — a human-readable phrase from {@code ReasonPhrase}</li>
 * </ul>
 * </p>
 *
 * <p>
 * Status code categories include:
 * <ul>
 *     <li><strong>1xx</strong> – Informational</li>
 *     <li><strong>2xx</strong> – Successful</li>
 *     <li><strong>3xx</strong> – Redirection</li>
 *     <li><strong>4xx</strong> – Client Error</li>
 *     <li><strong>5xx</strong> – Server Error</li>
 * </ul>
 * </p>
 *
 * <h3>Example usage:</h3>
 * <pre>{@code
 * HttpStatus status = HttpStatus.NOT_FOUND;
 * int code = status.value();                    // 404
 * String name = status.getErrorCode();          // "NOT_FOUND"
 * String phrase = status.getReasonPhrase();     // "Not Found"
 * }</pre>
 *
 * @author Ramon
 * @version 1.0.0
 * @since 2025-07-15
 */
public enum HttpStatus implements StatusCode {
    // Informational
    CONTINUE(100),
    SWITCHING_PROTOCOLS(101),
    PROCESSING(102),
    EARLY_HINTS(103),

    // Successful
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NON_AUTHORITATIVE(203),
    NO_CONTENT(204),
    RESET_CONTENT(205),
    PARTIAL_CONTENT(206),

    // Redirection
    MOVED_PERMANENTLY(301),
    MOVED_TEMPORARILY(302),
    NOT_MODIFIED(304),
    TEMPORARY_REDIRECT(307),

    // Client Error
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    PAYMENT_REQUIRED(402),
    FORBIDDEN(403),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    NOT_ACCEPTABLE(406),
    PROXY_AUTHENTICATION_REQUIRED(407),
    REQUEST_TIMEOUT(408),
    CONFLICT(409),
    GONE(410),
    LENGTH_REQUIRED(411),
    PRECONDITION_FAILED(412),
    PAYLOAD_TOO_LARGE(413),
    REQUEST_URI_TOO_LARGE(414),
    UNSUPPORTED_MEDIA_TYPE(415),
    REQUESTED_RANGE_NOT_SATISFIABLE(416),
    EXPECTATION_FAILED(417),
    I_AM_TEAPOT(418),
    INSUFFICIENT_SPACE(419),
    METHOD_FAILURE(420),
    DESTINATION_LOCKED(421),
    UNPROCESSABLE_ENTITY(422),
    LOCKED(423),
    FAILED_DEPENDENCY(424),
    TOO_EARLY(425),
    UPGRADE_REQUIRED(426),
    PRECONDITION_REQUIRED(428),
    TOO_MANY_REQUEST(429),

    // Server Error
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVER_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504),
    HTTP_VERSION_NOT_SUPPORTED(505),
    VARIANT_ALSO_NEGOTIATES(506),
    INSUFFICIENT_STORAGE(507),
    LOOP_DETECTED(508),
    BANDWIDTH_LIMIT_EXCEEDED(509),
    NOT_EXTENDED(510),
    NETWORK_AUTHENTICATION_REQUIRED(511);

    private final int value;
    private static final HttpStatus[] VALUES = values();

    /**
     * Constructs an {@code HttpStatus} enum constant with the given HTTP status code.
     *
     * @param value the integer HTTP status code
     */
    HttpStatus(int value) {
        this.value = value;
    }

    /**
     * Returns the numeric HTTP status code associated with this constant.
     *
     * @return the HTTP status code as an integer (e.g., 200, 404)
     */
    @Override
    public int value() {
        return value;
    }

    /**
     * Returns a standardized {@link HttpStatusCode} object for this status code.
     *
     * @return the {@link HttpStatusCode} corresponding to this HTTP status
     */
    public HttpStatusCode getHttpStatusCode() {
        return HttpStatusCode.valueOf(value);
    }

    /**
     * Resolves the {@code HttpStatus} enum constant matching the given integer code.
     *
     * @param value the integer HTTP status code
     * @return the corresponding {@code HttpStatus}, or {@code null} if no match is found
     */
    @Nullable
    public static HttpStatus resolve(int value) {
        for(HttpStatus status: VALUES) {
            if(status.value == value) {
                return status;
            }
        }
        return null;
    }

    /**
     * Returns the {@code HttpStatus} for the given numeric value.
     * <p>
     * This method throws an exception if no matching constant is found.
     * </p>
     *
     * @param value the integer HTTP status code
     * @return the matching {@code HttpStatus}
     * @throws EnumConstantNotPresentException if no constant matches the given value
     */
    public static HttpStatus valueOf(int value) {
        HttpStatus status = resolve(value);
        if(status == null) {
            throw new EnumConstantNotPresentException(HttpStatus.class, String.valueOf(value));
        }
        return status;
    }
}