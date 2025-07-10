package com.git.rrc.conf;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageSourceConfigTest {

    MessageSourceConfig config;

    @Test
    @DisplayName("Should not return null value")
    void messageSource_shouldNotReturnNullValue(){
        config = new MessageSourceConfig();
        assertNotNull(config.messageResource());
    }
}