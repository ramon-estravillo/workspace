package com.git.rrc.bo;

import com.git.rrc.cons.CodeConstants;

public class UnsupportedOperationException extends ProcessingException {

    public UnsupportedOperationException(String detail) {
        this(detail, new Throwable("Unsupported operation exception thrown"));
    }

    public UnsupportedOperationException(String detail, Throwable cause) {
        super(CodeConstants.UNSUPPORTED_OPERATION.getErrorCode(), detail, cause);
    }

}
