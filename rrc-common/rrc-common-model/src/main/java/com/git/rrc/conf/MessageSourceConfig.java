package com.git.rrc.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@SuppressWarnings("all")
public class MessageSourceConfig {

    @Bean
    @Primary
    @Qualifier("messageSourceDao")
    public MessageSource messageResource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("error/messages", "http/messages");
        source.setFallbackToSystemLocale(false);
        source.setDefaultEncoding("UTF-8");
        return source;
    }

}
