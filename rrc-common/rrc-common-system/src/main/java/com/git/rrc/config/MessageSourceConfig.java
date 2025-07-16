package com.git.rrc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Configuration class for defining message sources used to resolve localized
 * reason phrases for application-specific error codes and standard HTTP status codes.
 *
 * <p>
 * This configuration defines two {@link MessageSource} beans:
 * <ul>
 *     <li>{@code errorCodeReasonPhrase} — for application {@code ErrorCode} messages</li>
 *     <li>{@code httpStatusReasonPhrase} — for standard {@code HttpStatus} messages</li>
 * </ul>
 * The message files should be placed under {@code src/main/resources} with the following base paths:
 * <ul>
 *     <li>{@code error/messages.properties} for application error codes</li>
 *     <li>{@code http/messages.properties} for HTTP status codes</li>
 * </ul>
 * </p>
 *
 * <p>
 * All message sources are configured to use UTF-8 encoding and will not fallback to the system locale.
 * </p>
 *
 * @author Ramon
 * @since 2025-07-16
 * @version 1.0.0
 */
@Configuration
public class MessageSourceConfig {

    /**
     * Defines the {@link MessageSource} bean for resolving reason phrases related to
     * application-specific {@code ErrorCode} values.
     *
     * @return a configured {@link ResourceBundleMessageSource} using {@code error/messages} as its base name
     */
    @Bean(name = "errorCodeReasonPhrase")
    public MessageSource errorCodeReasonPhrase() {
        ResourceBundleMessageSource resourceBundleMessageSource = resourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("error/messages");
        return resourceBundleMessageSource;
    }

    /**
     * Defines the {@link MessageSource} bean for resolving reason phrases related to
     * standard {@code HttpStatus} values.
     *
     * @return a configured {@link ResourceBundleMessageSource} using {@code http/messages} as its base name
     */
    @Bean(name = "httpStatusReasonPhrase")
    public MessageSource httpStatusReasonPhrase() {
        ResourceBundleMessageSource resourceBundleMessageSource = resourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("http/messages");
        return resourceBundleMessageSource;
    }

    /**
     * Creates a base {@link ResourceBundleMessageSource} with common configuration.
     * <ul>
     *     <li>UTF-8 encoding</li>
     *     <li>No fallback to system locale</li>
     * </ul>
     *
     * @return a new {@link ResourceBundleMessageSource} instance
     */
    private ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setFallbackToSystemLocale(false);
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }

}
