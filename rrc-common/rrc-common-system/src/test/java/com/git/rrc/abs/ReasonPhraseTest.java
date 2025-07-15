package com.git.rrc.abs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

import static org.junit.jupiter.api.Assertions.*;

class ReasonPhraseTest {

    ReasonPhrase reasonPhrase;

    @BeforeEach
    void setUp() {
        ResourceBundleMessageSource bundler = new ResourceBundleMessageSource();
        bundler.setBasenames("error/messages", "http/messages");
        bundler.setFallbackToSystemLocale(false);
        bundler.setDefaultEncoding("UTF-8");

        reasonPhrase = new ReasonPhrase(bundler);
    }

    @Test
    @DisplayName("Should return http status message when value exist")
    void valueOf_shouldReturnHttpStatusMessage_whenValueExist() {
        assertEquals("Bad Request", ReasonPhrase.valueOf(400));
    }

    @Test
    @DisplayName("Should return error code message when value exist")
    void valueOf_shouldReturnErrorCodeMessage_whenValueExist() {
        assertEquals("Resource does not exist", ReasonPhrase.valueOf(2100));
    }

    @Test
    @DisplayName("Should throw NoSuchMessageException when value does not exist")
    void valueOf_shouldThrowNoSuchMessageException_whenValueDoesNotExist() {
        assertThrows(NoSuchMessageException.class, () -> ReasonPhrase.valueOf(0));
    }

}