package com.git.rrc.bo;

import com.git.rrc.cons.CodeConstants;
import com.git.rrc.dto.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends ProcessingException {

    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this(null, errors);
    }

    public ValidationException(String detail, List<Error> errors) {
        this(detail, errors, new Throwable("Validation exception thrown"));
    }

    public ValidationException(List<Error> errors, Throwable cause) {
        this(null, errors, cause);
    }

    public ValidationException(String detail, List<Error> errors, Throwable cause) {
        super(CodeConstants.VALIDATION_ERROR.getErrorCode(), detail, cause);
        this.errors = errors;
    }

}
