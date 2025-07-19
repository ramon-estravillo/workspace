package com.git.rrc.core.exception;

import com.git.rrc.core.exception.constant.ErrorCode;
import com.git.rrc.core.exception.constant.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ApplicationException Unit Tests")
class ApplicationExceptionTest extends BaseExceptionTest {

    private static final Throwable CAUSE = new Throwable();
    private static final String MESSAGE = "Underlying Error";

    @Test
    @DisplayName("Constructor with message")
    void testConstructorWithMessage() {
        ApplicationException exception = new ApplicationException(MESSAGE);

        assertFalse(exception.isPresentErrorCode());
        assertFalse(exception.isPresentHttpStatus());
        assertEquals(MESSAGE, exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with cause")
    void testConstructorWithCause() {
        ApplicationException exception = new ApplicationException(CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertFalse(exception.isPresentErrorCode());
        assertFalse(exception.isPresentHttpStatus());
    }

    @Test
    @DisplayName("Constructor with message and cause")
    void testConstructorWithMessageAndCause() {
        ApplicationException exception = new ApplicationException(MESSAGE, CAUSE);

        assertNull(exception.getErrorCode());
        assertNull(exception.getHttpStatus());
        assertEquals(CAUSE, exception.getCause());
        assertEquals(MESSAGE, exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with ErrorCode")
    void testConstructorWithErrorCode() {
        verifyReasonPhraseResolverMocked(true);
        ApplicationException exception = new ApplicationException(ErrorCode.BAD_REQUEST);

        assertNotNull(exception.getErrorCode());
        assertTrue(exception.isPresentErrorCode());
        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
        assertEquals("Bad request.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with ErrorCode and cause")
    void testConstructorWithErrorCodeAndCause() {
        verifyReasonPhraseResolverMocked(true);
        ApplicationException exception = new ApplicationException(ErrorCode.REQUEST_CONFLICT, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertTrue(exception.isPresentErrorCode());
        assertEquals(ErrorCode.REQUEST_CONFLICT, exception.getErrorCode());
        assertEquals("Request conflict.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with ErrorCode, message, and cause")
    void testConstructorWithErrorCodeMessageAndCause() {
        ApplicationException exception = new ApplicationException(ErrorCode.BAD_REQUEST, MESSAGE, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertEquals(MESSAGE, exception.getMessage());
        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
    }

    @Test
    @DisplayName("Constructor with ErrorCode, HttpStatus, and cause")
    void testConstructorWithErrorCodeHttpStatusAndCause() {
        verifyReasonPhraseResolverMocked(true);
        ApplicationException exception = new ApplicationException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertTrue(exception.isPresentErrorCode());
        assertTrue(exception.isPresentHttpStatus());
        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Constructor with ErrorCode, HttpStatus, message, and cause")
    void testConstructorWithErrorCodeHttpStatusMessageAndCause() {
        ApplicationException exception = new ApplicationException(
                ErrorCode.REQUEST_CONFLICT, HttpStatus.CONFLICT, MESSAGE, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertEquals(MESSAGE, exception.getMessage());
        assertTrue(exception.isPresentErrorCode());
        assertTrue(exception.isPresentHttpStatus());
    }

    @Test
    @DisplayName("Constructor with raw code, message and cause")
    void testConstructorWithIntCodeMessageAndCause() {
        ApplicationException exception = new ApplicationException(2003, MESSAGE, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertEquals(MESSAGE, exception.getMessage());
        assertFalse(exception.isPresentHttpStatus());
        assertEquals(ErrorCode.REQUEST_CONFLICT, exception.getErrorCode());
    }

    @Test
    @DisplayName("Constructor with unresolve raw code and cause")
    void testConstructorWithUnresolveIntCodeAndCause() {
        ApplicationException exception = new ApplicationException(0, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertFalse(exception.isPresentErrorCode());
        assertFalse(exception.isPresentHttpStatus());
        assertEquals("0", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with raw code, status, and cause")
    void testConstructorWithCodeAndStatusInts_Cause() {
        verifyReasonPhraseResolverMocked(true);
        ApplicationException exception = new ApplicationException(2000, 400, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertTrue(exception.isPresentErrorCode());
        assertTrue(exception.isPresentHttpStatus());
        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Constructor with raw code, unresolve status, message and cause")
    void testConstructorWithIntCodeAndUnresolveStatus_MessageAndCause() {
        ApplicationException exception = new ApplicationException(2000, 520, MESSAGE, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertEquals(MESSAGE, exception.getMessage());
        assertTrue(exception.isPresentErrorCode());
        assertFalse(exception.isPresentHttpStatus());
        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
    }
}