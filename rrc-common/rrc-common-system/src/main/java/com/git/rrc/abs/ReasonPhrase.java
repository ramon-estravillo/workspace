package com.git.rrc.abs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Utility class for resolving error or status code integers to localized reason phrases.
 * <p>
 * This class retrieves messages from a {@link MessageSource} (typically backed by message property files),
 * allowing the application to map numeric codes (e.g., error or status codes) to human-readable, localized descriptions.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * String message = ReasonPhrase.valueOf(2000); // e.g., "Bad Request"
 * }</pre>
 * </p>
 */
@Component
@SuppressWarnings("all")
@Deprecated
public class ReasonPhrase {

    /**
     * The message source used to resolve code descriptions.
     * This is injected via Spring's dependency injection mechanism.
     */
    private static MessageSource source;

    /**
     * Constructs the {@code ReasonPhrase} utility by injecting the message source bean.
     *
     * @param source the message source bean, typically configured with application message properties
     */
    @Autowired
    public ReasonPhrase(@Qualifier("messageSourceDao") MessageSource source) {
        ReasonPhrase.source = source;
    }

    /**
     * Resolves the given numeric code to its corresponding reason phrase using the system default locale.
     *
     * @param value the numeric code (e.g., 2000)
     * @return the localized reason phrase corresponding to the code
     */
    public static String valueOf(int value) {
        return valueOf(value, Locale.getDefault());
    }

    /**
     * Resolves the given numeric code to its corresponding reason phrase using the specified locale.
     *
     * @param value  the numeric code (e.g., 2000)
     * @param locale the locale to use for message resolution
     * @return the localized reason phrase corresponding to the code
     */
    public static String valueOf(int value, Locale locale) {
        return valueOf(value, null, locale);
    }

    /**
     * Resolves the given numeric code to its corresponding reason phrase using the specified arguments and locale.
     * This allows for dynamic message formatting.
     *
     * @param value  the numeric code (e.g., 2000)
     * @param args   optional arguments for message formatting
     * @param locale the locale to use for message resolution
     * @return the formatted and localized reason phrase corresponding to the code
     */
    public static String valueOf(int value, Object[] args, Locale locale) {
        return source.getMessage(String.valueOf(value), args, locale);
    }
}
