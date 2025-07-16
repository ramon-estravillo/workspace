package com.git.rrc.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean(name = "errorCodeReasonPhrase")
    public MessageSource errorCodeReasonPhrase() {
        ResourceBundleMessageSource resourceBundleMessageSource = resourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("error/messages");
        return resourceBundleMessageSource;
    }

    @Bean(name = "httpStatusReasonPhrase")
    public MessageSource httpStatusReasonPhrase() {
        ResourceBundleMessageSource resourceBundleMessageSource = resourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("http/messages");
        return resourceBundleMessageSource;
    }

    private ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setFallbackToSystemLocale(false);
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }
}
