package com.git.rrc.con;

import com.git.rrc.abs.ReasonPhrase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatusCode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HttpStatusTest {

    @Test
    @DisplayName("Should return the numeric value of the enum")
    void value_shouldReturnNumericValue() {
        assertEquals(400, HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return the name of the enum")
    void getErrorCode_shouldReturnConstantName() {
        assertEquals("BAD_GATEWAY", HttpStatus.BAD_GATEWAY.getErrorCode());
    }

    @Test
    @DisplayName("Should return the detailed error message")
    void getReasonPhrase_shouldReturnHttpStatusDetailMessage() {
        try(MockedStatic<ReasonPhrase> reasonPhraseMockedStatic = mockStatic(ReasonPhrase.class)) {
            reasonPhraseMockedStatic.when(() -> ReasonPhrase.valueOf(200)).thenReturn("OK");

            assertEquals("OK", HttpStatus.OK.getReasonPhrase());

            reasonPhraseMockedStatic.verify(() -> ReasonPhrase.valueOf(200));
        }
    }

    @Test
    @DisplayName("Should return the associated HttpStatusCode")
    void getHttpStatusCode_shouldReturnHttpStatusCode() {
        HttpStatusCode httpStatusCode = HttpStatus.OK.getHttpStatusCode();
        assertNotNull(httpStatusCode);
        assertTrue(httpStatusCode.is2xxSuccessful());
    }

    @Test
    @DisplayName("Should return the associated HttpStatus")
    void valueOf_shouldReturnHttpStatus() {
        assertNotNull(HttpStatus.valueOf(500));
    }

    @Test
    @DisplayName("Should throw an EnumConstantNotPresentException")
    void valueOf_shouldThrowEnumConstantNotPresentException() {
        assertThrows(EnumConstantNotPresentException.class, () -> HttpStatus.valueOf(110));
    }
}