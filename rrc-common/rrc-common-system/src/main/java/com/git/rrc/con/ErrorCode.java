package com.git.rrc.con;

import com.git.rrc.abs.CodeConstant;
import com.git.rrc.abs.ReasonPhrase;

public enum ErrorCode implements CodeConstant {
    PROCESSING_FAILED(1100),
    RESOURCE_NOT_FOUND(2100),
    PRE_CONDITION_FAILED(2101),
    VALIDATION_FAILED(2102),
    MISSING_REQUIREMENTS(2103),
    INTERNAL_ERROR(3100),
    NOT_YET_IMPLEMENTED(3101);

    private final int value;
    private static final ErrorCode[] VALUES = values();

    ErrorCode(int value) {
        this.value = value;
    }

    @Override
    public String getErrorCode() {
        return name();
    }

    @Override
    public String getReasonPhrase() {
        return ReasonPhrase.valueOf(value);
    }

    public static ErrorCode resolve(int value) {
        for(ErrorCode code: VALUES) {
            if(code.value == value) {
                return code;
            }
        }
        return null;
    }

    public static ErrorCode valueOf(int value) {
        ErrorCode code = resolve(value);
        if(code == null) {
            throw new EnumConstantNotPresentException(ErrorCode.class, String.valueOf(value));
        }
        return code;
    }
}
