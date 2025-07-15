package com.git.rrc.con;

import com.git.rrc.abs.ReasonPhrase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class ErrorCodeTest {

    @Test
    @DisplayName("Should return corresponding name of the ErrorCode")
    void getErrorCode_shouldReturnConstantName_whenValueIsPresent() {
        ErrorCode code = ErrorCode.valueOf(2000);
        assertEquals("BAD_REQUEST", code.getErrorCode());
    }

    @Test
    @DisplayName("T")
    void getReasonPhrase_shouldErrorCodeDetailedMessage_whenValueIsPresent() {
        try(MockedStatic<ReasonPhrase> reasonPhraseMockedStatic = mockStatic(ReasonPhrase.class)) {
            reasonPhraseMockedStatic.when(() -> ReasonPhrase.valueOf(2000)).thenReturn("Bad Request");

            String actual = ErrorCode.BAD_REQUEST.getReasonPhrase();
            assertEquals("Bad Request", actual);
            reasonPhraseMockedStatic.verify(() -> ReasonPhrase.valueOf(2000));
        }
    }

    @Test
    @DisplayName("Should return corresponding ErrorCode")
    void valueOf_shouldReturnErrorCode_whenValueIsPresent() {
        assertNotNull(ErrorCode.valueOf(2400));
    }

    @Test
    @DisplayName("Should throw an EnumConstantNotPresentException")
    void valueOf_shouldThrowEnumConstantNotPresentException_whenValueIsNotPresent() {
        try {
            ErrorCode.valueOf(1000);
        } catch (Exception e) {
            if(!(e instanceof EnumConstantNotPresentException)) {
                fail("EnumConstantNotPresentException expected but instead got " + e);
            }
        }
    }

}