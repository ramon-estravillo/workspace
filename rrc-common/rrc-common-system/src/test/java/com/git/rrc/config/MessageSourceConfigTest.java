package com.git.rrc.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class MessageSourceConfigTest {

    private MessageSource source;
    private MessageSourceConfig config;

    private static final Locale LOCALE = Locale.getDefault();

    @BeforeEach
    void setUp() {
        config = new MessageSourceConfig();
    }

    @Test
    @DisplayName("Should return error message only")
    void errorCodeReasonPhrase_shouldReturnErrorCodeMessages() {
        source = config.errorCodeReasonPhrase();
        String excepted = "Internal server error. Please contact support";

        assertNotNull(source);
        assertEquals(excepted, source.getMessage("3100", null, LOCALE));
        assertThrows(NoSuchMessageException.class, () -> source.getMessage("400", null, LOCALE));
    }

    @Test
    @DisplayName("Should return http status message only")
    void httpStatusReasonPhrase_shouldReturnHttpStatusMessages() {
        source = config.httpStatusReasonPhrase();

        assertNotNull(source);
        assertEquals("Bad Request", source.getMessage("400", null, LOCALE));
        assertThrows(NoSuchMessageException.class, () -> source.getMessage("3100", null, LOCALE));
    }

}