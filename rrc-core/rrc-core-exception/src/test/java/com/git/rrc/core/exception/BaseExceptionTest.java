package com.git.rrc.core.exception;

import com.git.rrc.core.exception.constant.ErrorCode;
import com.git.rrc.core.exception.constant.ReasonPhraseResolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockedStatic;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

abstract class BaseExceptionTest {

    private boolean verify = false;
    MockedStatic<ReasonPhraseResolver> reasonPhraseResolverMockedStatic;

    @BeforeEach
    void setUp() {
        verifyReasonPhraseResolverMocked(false);
        reasonPhraseResolverMockedStatic = mockStatic(ReasonPhraseResolver.class);
        reasonPhraseResolverMockedStatic.when(() -> ReasonPhraseResolver.valueOf(any(ErrorCode.class)))
                .thenAnswer(invocationOnMock -> {
                    ErrorCode code = invocationOnMock.getArgument(0);

                    return switch (code) {
                        case BAD_REQUEST -> "Bad request.";
                        case RESOURCE_NOT_FOUND -> "Resource does not exist.";
                        case INTERNAL_SERVER_ERROR -> "Internal server error.";
                        case REQUEST_CONFLICT -> "Request conflict.";
                        case VALIDATION_FAILED -> "Validation failure";
                        default -> throw new EnumConstantNotPresentException(ErrorCode.class, String.valueOf(code.value()));
                    };
                });
    }

    @AfterEach
    void cleanUp() {
        if(verify)
            reasonPhraseResolverMockedStatic.verify(() -> ReasonPhraseResolver.valueOf(any(ErrorCode.class)));

        reasonPhraseResolverMockedStatic.close();
    }

    void verifyReasonPhraseResolverMocked(boolean verify) {
        this.verify = verify;
    }
}
