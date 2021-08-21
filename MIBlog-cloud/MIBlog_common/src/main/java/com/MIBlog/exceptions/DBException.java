package com.MIBlog.exceptions;

import com.MIBlog.enums.constant.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * @author asuna
 */
public class DBException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public DBException(ErrorCode errorCode) {
        this(errorCode.getMessage(), errorCode, HttpStatus.BAD_REQUEST);
    }

    public DBException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
