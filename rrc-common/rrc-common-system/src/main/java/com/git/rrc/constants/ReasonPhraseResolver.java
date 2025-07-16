package com.git.rrc.constants;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Centralized resolver for localized reason phrases associated with {@link StatusCode} enums
 * such as {@link ErrorCode} and {@link HttpStatus}.
 *
 * <p>
 * This resolver delegates message lookup to different {@link MessageSource} instances based on the
 * type of enum provided:
 * <ul>
 *     <li>{@code ErrorCode} enums use the {@code errorCodeReasonPhrase} message source</li>
 *     <li>{@code HttpStatus} enums use the {@code httpStatusReasonPhrase} message source</li>
 * </ul>
 * </p>
 *
 * <p>
 * This design enables enums to remain lightweight and free of Spring dependencies, while still
 * supporting localized descriptions through static utility methods.
 * </p>
 *
 * <p>
 * Message keys are derived from the {@link StatusCode#value()} method, which must return an integer
 * that matches the keys defined in the message bundles.
 * </p>
 *
 * <p><b>Note:</b> This class uses static fields to store message sources and assumes it is initialized
 * exactly once by the Spring context.</p>
 *
 * @author Ramon
 * @version 1.0.0
 * @since 2025-07-16
 */
@Component
@SuppressWarnings("all")
class ReasonPhraseResolver {

    private static MessageSource errorCodeMessageSource;
    private static MessageSource httpStatusMessageSource;

    /**
     * Initializes the static message sources used for resolving reason phrases.
     *
     * @param errorCodeMessageSource   the message source for application error codes
     * @param httpStatusMessageSource  the message source for HTTP status codes
     */
    public ReasonPhraseResolver(
            @Qualifier("errorCodeReasonPhrase")  MessageSource errorCodeMessageSource,
            @Qualifier("httpStatusReasonPhrase") MessageSource httpStatusMessageSource
    ) {
        ReasonPhraseResolver.errorCodeMessageSource  = errorCodeMessageSource;
        ReasonPhraseResolver.httpStatusMessageSource = httpStatusMessageSource;
    }

    /**
     * Determines which message source to use based on the enum type.
     *
     * @param targetType the enum constant (must implement {@link StatusCode})
     * @return the appropriate message source
     */
    private static MessageSource getMessageSource(Enum<?> targetType) {
        return targetType instanceof ErrorCode
                ? ReasonPhraseResolver.errorCodeMessageSource
                : ReasonPhraseResolver.httpStatusMessageSource;
    }

    /**
     * Extracts the message code (as a string) from the enum's integer {@code value()}.
     *
     * @param source the enum constant
     * @return the string representation of the code, used as a message key
     */
    private static String getCode(Enum<?> source) {
        return String.valueOf(((StatusCode) source).value());
    }

    /**
     * Resolves the localized reason phrase for the given enum using the default locale.
     *
     * @param source the enum constant (must implement {@link StatusCode})
     * @return the localized message, or a fallback if not found
     */
    public static String valueOf(Enum<?> source) {
        return valueOf(source, Locale.getDefault());
    }

    /**
     * Resolves the localized reason phrase for the given enum using the specified locale.
     *
     * @param source the enum constant (must implement {@link StatusCode})
     * @param locale the locale to use for message resolution
     * @return the localized message, or a fallback if not found
     */
    public static String valueOf(Enum<?> source, Locale locale) {
        return getMessageSource(source).getMessage(getCode(source), null, locale);
    }

    /**
     * Resolves the localized reason phrase with arguments for parameterized messages.
     *
     * @param source the enum constant (must implement {@link StatusCode})
     * @param args   arguments to replace placeholders in the message
     * @param locale the locale to use for message resolution
     * @return the localized and formatted message
     */
    public static String valueOf(Enum<?> source, Object[] args, Locale locale) {
        return getMessageSource(source).getMessage(getCode(source), args, locale);
    }

}
