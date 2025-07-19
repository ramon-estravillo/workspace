package com.git.rrc.core.exception;

import com.git.rrc.core.exception.constant.ErrorCode;
import com.git.rrc.core.exception.constant.HttpStatus;
import com.git.rrc.core.exception.dto.Error;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BusinessRuleViolationException Unit Tests")
class BusinessRuleViolationExceptionTest extends BaseExceptionTest {

    private static final Throwable CAUSE = new Throwable();
    private static final String MESSAGE = "Violation error";
    private static final List<Error> ERROR_LIST = Collections.singletonList(
            new Error("email", "ea@mail.com", "Invalid email value")
    );

    @Test
    @DisplayName("Constructor with default error code and HTTP status and an optional message")
    void testConstructorWithMessage() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(MESSAGE);

        assertFalse(exception.isPresentErrorCode());
        assertTrue(exception.isPresentHttpStatus());
        assertEquals(MESSAGE, exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with message and a list of validation or business errors.")
    void testConstructorWithMessageAndErrors() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(MESSAGE, ERROR_LIST);

        assertFalse(exception.getErrors().isEmpty());
        assertEquals(MESSAGE, exception.getMessage());
        assertEquals(ERROR_LIST.get(0), exception.getErrors().get(0));
    }

    @Test
    @DisplayName("Constructor with message and root cause")
    void testConstructorWithMessageAndCause() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(MESSAGE, CAUSE);

        assertEquals(CAUSE, exception.getCause());
        assertTrue(exception.getErrors().isEmpty());
        assertEquals(MESSAGE, exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with message, cause and list of errors")
    void testConstructorWithMessageCauseAndErrors() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(MESSAGE, CAUSE, ERROR_LIST);

        assertEquals(CAUSE, exception.getCause());
        assertFalse(exception.getErrors().isEmpty());
        assertFalse(exception.isPresentErrorCode());
        assertTrue(exception.isPresentHttpStatus());
    }

    @Test
    @DisplayName("Constructor with ErrorCode, message, cause, and list of errors")
    void testConstructorWithErrorCodeMessageCauseAndErrors() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(
                ErrorCode.REQUEST_CONFLICT, MESSAGE, ERROR_LIST, CAUSE);

        assertFalse(exception.getErrors().isEmpty());
        assertEquals(MESSAGE, exception.getMessage());
        assertEquals(ErrorCode.REQUEST_CONFLICT, exception.getErrorCode());
    }

    @Test
    @DisplayName("Constructor with ErrorCode and error list")
    void testConstructorWithErrorCodeAndErrors() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(ErrorCode.VALIDATION_FAILED, ERROR_LIST);

        assertEquals(ErrorCode.VALIDATION_FAILED, exception.getErrorCode());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    @DisplayName("Constructor with error code, HTTP status, and error list")
    void testConstructorWithErrorCodeStatusAndErrors() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(
                ErrorCode.REQUEST_CONFLICT, HttpStatus.CONFLICT, ERROR_LIST);

        assertEquals(ErrorCode.REQUEST_CONFLICT, exception.getErrorCode());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
        assertEquals(ERROR_LIST, exception.getErrors());
    }

    @Test
    @DisplayName("Constructor with all arguments")
    void testConstructorWithAllArguments() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(
                ErrorCode.REQUEST_CONFLICT,
                HttpStatus.CONFLICT,
                MESSAGE, ERROR_LIST,
                new Throwable("Invalid argument")
        );

        assertEquals(ErrorCode.REQUEST_CONFLICT, exception.getErrorCode());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
        assertEquals(ERROR_LIST, exception.getErrors());
    }

    @Test
    @DisplayName("Constructor with raw unresolve code")
    void testConstructorWithUnresolveIntCode() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(-1, ERROR_LIST);
        assertFalse(exception.isPresentErrorCode());
    }

    @Test
    @DisplayName("Constructor with raw and unresolve code, HTTP status and list of error")
    void testConstructorWithUnresolveCodeAndStatusInts() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(-1, 520, Collections.emptyList());
        assertTrue(exception.getErrors().isEmpty());
        assertFalse(exception.isPresentErrorCode());
        assertFalse(exception.isPresentHttpStatus());
    }

    @Test
    @DisplayName("Constructor with raw error code, message and list of error")
    void testConstructorWithCodeIntMessageAndErrors() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(-1, MESSAGE, null);
        assertNull(exception.getErrors());
        assertFalse(exception.isPresentErrorCode());
        assertEquals(MESSAGE, exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with raw error code, root cause, and list of error")
    void testConstructorWithCodeIntCauseAndErrors() {
        BusinessRuleViolationException exception = new BusinessRuleViolationException(2000, CAUSE, ERROR_LIST);
        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
        assertEquals(CAUSE, exception.getCause());
        assertEquals(ERROR_LIST, exception.getErrors());
    }
}