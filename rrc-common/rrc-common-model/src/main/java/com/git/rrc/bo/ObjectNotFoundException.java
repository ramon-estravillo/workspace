package com.git.rrc.bo;

import com.git.rrc.cons.CodeConstants;

public class ObjectNotFoundException extends ProcessingException {

    public ObjectNotFoundException() {
        this(null);
    }

    public ObjectNotFoundException(String detail) {
        this(detail, new Throwable("Object not found exception thrown"));
    }

    public ObjectNotFoundException(String detail, Throwable cause) {
        super(CodeConstants.OBJECT_NOT_FOUND.getErrorCode(), detail, cause);
    }
}
