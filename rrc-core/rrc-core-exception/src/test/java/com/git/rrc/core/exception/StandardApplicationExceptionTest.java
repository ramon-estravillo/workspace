package com.git.rrc.core.exception;

import com.git.rrc.core.exception.constant.ErrorCode;
import com.git.rrc.core.exception.constant.HttpStatus;
import com.git.rrc.core.exception.constant.ReasonPhraseResolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mockStatic;

@DisplayName("StandardApplicationException Unit Tests")
class StandardApplicationExceptionTest {

    MockedStatic<ReasonPhraseResolver> reasonPhraseResolverMockedStatic;

    @BeforeEach
    void setUp() {
        reasonPhraseResolverMockedStatic = mockStatic(ReasonPhraseResolver.class);
        reasonPhraseResolverMockedStatic.when(() -> ReasonPhraseResolver.valueOf(any(ErrorCode.class)))
                .thenAnswer(invocationOnMock -> {
                    ErrorCode code = invocationOnMock.getArgument(0);

                    return switch (code) {
                        case BAD_REQUEST -> "Bad request. Please check input parameters.";
                        case RESOURCE_NOT_FOUND -> "Resource does not exist.";
                        case INTERNAL_SERVER_ERROR -> "Internal server error. Please try again later.";
                        case REQUEST_CONFLICT -> "Request conflict. Operation cannot proceed due to current resource state.";
                        default -> throw new EnumConstantNotPresentException(ErrorCode.class, String.valueOf(code.value()));
                    };
                });
    }

    @AfterEach
    void cleanUp() {
        reasonPhraseResolverMockedStatic.close();
    }

    @Test
    @DisplayName("Default constructor sets INTERNAL_SERVER_ERROR code and status")
    void testDefaultConstructor() {
        StandardApplicationException exception = new StandardApplicationException();

        assertEquals(ErrorCode.INTERNAL_SERVER_ERROR, exception.getErrorCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
        assertEquals("Internal server error. Please try again later.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with ErrorCode sets default HTTP status")
    void testConstructorWithErrorCode() {
        StandardApplicationException exception = new StandardApplicationException(ErrorCode.BAD_REQUEST);

        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
        assertEquals("Bad request. Please check input parameters.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with ErrorCode and HttpStatus sets both correctly")
    void testConstructorWithErrorCodeAndStatus() {
        StandardApplicationException exception = new StandardApplicationException(
                ErrorCode.RESOURCE_NOT_FOUND,
                HttpStatus.NOT_FOUND
        );

        assertEquals(ErrorCode.RESOURCE_NOT_FOUND, exception.getErrorCode());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals("Resource does not exist.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with int code and status maps to proper enums")
    void testConstructorWithCodeAndStatusInts() {
        int code = ErrorCode.BAD_REQUEST.value();
        int status = HttpStatus.UNAUTHORIZED.value();

        StandardApplicationException exception = new StandardApplicationException(code, status);

        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getHttpStatus());
        assertEquals("Bad request. Please check input parameters.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with ErrorCode, HttpStatus and cause works correctly")
    void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Underlying error");
        StandardApplicationException exception = new StandardApplicationException(
                ErrorCode.REQUEST_CONFLICT,
                HttpStatus.CONFLICT,
                cause
        );

        assertEquals(ErrorCode.REQUEST_CONFLICT, exception.getErrorCode());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
        assertEquals(cause, exception.getCause());
        assertEquals("Request conflict. Operation cannot proceed due to current resource state.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor with int code maps to proper enum")
    void testConstructorWithCodeInt() {
        int code = ErrorCode.REQUEST_CONFLICT.value();
        StandardApplicationException exception = new StandardApplicationException(code);

        assertEquals(ErrorCode.REQUEST_CONFLICT, exception.getErrorCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getHttpStatus());
    }

    @Test
    @DisplayName("")
    void testConstructorWithCodeAndStatusIntsAndCause() {
        int code = ErrorCode.BAD_REQUEST.value();
        int status = HttpStatus.I_AM_TEAPOT.value();
        Throwable cause = new Throwable("I am teapot");
        StandardApplicationException exception = new StandardApplicationException(code, status, cause);

        assertEquals(ErrorCode.BAD_REQUEST, exception.getErrorCode());
        assertEquals(HttpStatus.I_AM_TEAPOT, exception.getHttpStatus());
        assertEquals(cause, exception.getCause());
    }

}