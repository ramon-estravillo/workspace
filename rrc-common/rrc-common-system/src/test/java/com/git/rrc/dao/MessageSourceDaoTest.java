package com.git.rrc.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageSourceDaoTest {

    MessageSourceDao config;

    @Test
    @DisplayName("Should not return null value")
    void messageSource_shouldNotReturnNullValue(){
        config = new MessageSourceDao();
        assertNotNull(config.messageResource());
    }

}