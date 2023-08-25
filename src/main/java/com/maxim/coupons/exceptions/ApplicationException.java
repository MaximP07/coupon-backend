package com.maxim.coupons.exceptions;

import com.maxim.coupons.enums.ErrorType;

public class ApplicationException extends Exception {
    private ErrorType errorType;

    public ApplicationException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ApplicationException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ApplicationException(ErrorType errorType, String message, Exception innerException) {
        super(message, innerException);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
