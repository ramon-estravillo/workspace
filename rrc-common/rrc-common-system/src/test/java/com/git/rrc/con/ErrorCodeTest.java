package com.git.rrc.con;

import com.git.rrc.abs.ReasonPhrase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class ErrorCodeTest {

    @Test
    @DisplayName("Should return the name of the enum constant")
    void getErrorCode_shouldReturnConstantName() {
        ErrorCode code = ErrorCode.valueOf(2000);
        assertEquals("BAD_REQUEST", code.getErrorCode());
    }

    @Test
    @DisplayName("Should return the detailed error message")
    void getReasonPhrase_shouldErrorCodeDetailedErrorMessage() {
        try(MockedStatic<ReasonPhrase> reasonPhraseMockedStatic = mockStatic(ReasonPhrase.class)) {
            reasonPhraseMockedStatic.when(() -> ReasonPhrase.valueOf(2000)).thenReturn("Bad Request");

            String actual = ErrorCode.BAD_REQUEST.getReasonPhrase();
            assertEquals("Bad Request", actual);
            reasonPhraseMockedStatic.verify(() -> ReasonPhrase.valueOf(2000));
        }
    }

    @Test
    @DisplayName("Should return the enum constant")
    void valueOf_shouldReturnErrorCode() {
        assertNotNull(ErrorCode.valueOf(2400));
    }

    @Test
    @DisplayName("Should throw an EnumConstantNotPresentException")
    void valueOf_shouldThrowEnumConstantNotPresentException() {
        try {
            ErrorCode.valueOf(1000);
        } catch (Exception e) {
            if(!(e instanceof EnumConstantNotPresentException)) {
                fail("EnumConstantNotPresentException expected but instead got " + e);
            }
        }
    }

}