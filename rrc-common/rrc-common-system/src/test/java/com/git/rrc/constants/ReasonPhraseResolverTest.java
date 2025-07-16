package com.git.rrc.constants;

import com.git.rrc.config.MessageSourceConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReasonPhraseResolverTest {

    ReasonPhraseResolver resolver;

    @BeforeEach
    void setUp() {
        MessageSourceConfig config = new MessageSourceConfig();
        resolver = new ReasonPhraseResolver(config.errorCodeReasonPhrase(), config.httpStatusReasonPhrase());
    }

    @Test
    @DisplayName("Should return error message only")
    void valueOf_shouldReturnErrorCodeMessage() {
        assertEquals("Resource does not exist",
                ReasonPhraseResolver.valueOf(ErrorCode.RESOURCE_NOT_FOUND, null, Locale.ENGLISH));
    }

    @Test
    @DisplayName("Should return http status message only")
    void valueOf_shouldReturnHttpStatusMessage() {
        assertEquals("Bad Request", ReasonPhraseResolver.valueOf(HttpStatus.BAD_REQUEST));
    }

}