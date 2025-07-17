package com.git.rrc.constants;

/**
 * IMPORTANT:
 * This class uses Java sealed interfaces with the `permits` clause to enforce
 * a strict and controlled inheritance hierarchy.
 * 
 * SonarCloud flags this as a high-severity "acyclic dependency" issue because
 * the `permits` clause introduces a deliberate cyclic dependency between
 * the sealed interface and its permitted subclasses.
 * 
 * This cycle is required by the Java language specification for sealed types
 * and cannot be avoided without removing `sealed` and `permits`, which would
 * weaken type safety and design constraints.
 * 
 * Therefore, this maintainability issue is a known false positive, and can be
 * safely ignored to preserve the benefits of sealed interfaces.
 */

/**
 * Represents a generic contract for status codes used across the application or protocol layers.
 *
 * <p>
 * Implementations of {@code StatusCode} provide:
 * <ul>
 *     <li>A unique numeric {@code value()} used for communication or identification</li>
 *     <li>A human-readable {@code getReasonPhrase()} derived from {@link ReasonPhraseResolver}</li>
 *     <li>An {@code getErrorCode()} representing the enum constant name</li>
 * </ul>
 * </p>
 *
 * <p>
 * This sealed interface is implemented by enums such as {@link ErrorCode} (application errors)
 * and {@link HttpStatus} (HTTP protocol status codes). It supports consistent error serialization,
 * response mapping, and message resolution.
 * </p>
 *
 * <h3>Example usage:</h3>
 * <pre>{@code
 * StatusCode status = ErrorCode.AUTHENTICATION_FAILED;
 * int code = status.value();                    // 2200
 * String name = status.getErrorCode();          // "AUTHENTICATION_FAILED"
 * String phrase = status.getReasonPhrase();     // "Authentication failed"
 * }</pre>
 *
 * @see ErrorCode
 * @see HttpStatus
 * @see ReasonPhraseResolver
 */
sealed interface StatusCode permits ErrorCode, HttpStatus {

    /**
     * Returns the numeric value representing this status code.
     *
     * @return the integer code
     */
    int value();

    /**
     * Returns a human-readable reason phrase for this status.
     * <p>
     * Default implementation delegates to {@link ReasonPhraseResolver#valueOf(Enum)}.
     * </p>
     *
     * @return the reason phrase
     * @throws IllegalArgumentException if no phrase is available for the value
     */
    default String getReasonPhrase() {
        return ReasonPhraseResolver.valueOf((Enum<?>) this);
    }

    /**
     * Returns the enum constant name representing this status.
     *
     * @return the name of the implementing enum constant
     */
    default String getErrorCode() {
        return ((Enum<?>) this).name();
    }

}
