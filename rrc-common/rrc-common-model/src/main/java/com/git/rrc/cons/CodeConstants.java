package com.git.rrc.cons;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

public enum CodeConstants {
    VALIDATION_ERROR("RC-0111", HttpStatus.BAD_REQUEST, "Invalid Input."),
    BUSINESS_ERROR("RC-0111", HttpStatus.BAD_REQUEST, "Business requirements failed"),
    OBJECT_NOT_FOUND("RC-0202", HttpStatus.NOT_FOUND, "Resource does not exist."),
    INSUFFICIENT_ACCESS("RC-0991", HttpStatus.UNAUTHORIZED, "You are not authorized to access resource"),
    NOT_ALLOWED("RC-0992", HttpStatus.FORBIDDEN, "Please contact support"),
    TECHNICAL_ERROR("RC-0999", HttpStatus.INTERNAL_SERVER_ERROR, "Please contact support"),
    UNSUPPORTED_OPERATION("RC-0995", HttpStatus.INTERNAL_SERVER_ERROR, "Operation trying to trigger is currently not yet supported.");

    @Getter
    final String detail;
    @Getter
    final String errorCode;
    @Getter
    final HttpStatus status;

    CodeConstants(String errorCode, HttpStatus status, String detail) {
        this.detail = detail;
        this.status = status;
        this.errorCode = errorCode;
    }

    public enum HttpStatus {
        NOT_FOUND(404, "Not Found"),
        BAD_REQUEST(400, "Bad Request"),
        UNAUTHORIZED(401, "Unauthorized"),
        FORBIDDEN(403, "Forbidden Request"),
        INTERNAL_SERVER_ERROR(500, "Internal Server Error");

        final int value;
        @Getter
        final String reasonPhrase;
        @Getter
        final HttpStatusCode statusCode;

        HttpStatus(int value, String reasonPhrase) {
            this.value = value;
            this.reasonPhrase = reasonPhrase;
            this.statusCode = HttpStatusCode.valueOf(value);
        }
    }
}
