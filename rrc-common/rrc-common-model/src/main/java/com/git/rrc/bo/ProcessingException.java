package com.git.rrc.bo;

import com.git.rrc.cons.CodeConstants;
import lombok.Getter;

import java.util.Objects;

public class ProcessingException extends RuntimeException {

    private final String detail;
    @Getter
    private final String errorCode;

    public ProcessingException(String errorCode) {
        this(errorCode, null, new Throwable("Processing exception thrown"));
    }

    public ProcessingException(String errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    public ProcessingException(String errorCode, String detail, Throwable cause) {
        super(CodeConstants.valueOf(errorCode).getDetail(), cause);
        this.errorCode = errorCode;
        this.detail = detail;
    }

    public CodeConstants getReason() {
        return CodeConstants.valueOf(getErrorCode());
    }

    public String getDetail() {
        return Objects.isNull(detail)
                ? getMessage() : getReason().getDetail();
    }

}
