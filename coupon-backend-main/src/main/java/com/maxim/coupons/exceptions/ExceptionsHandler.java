package com.maxim.coupons.exceptions;

import com.maxim.coupons.enums.ErrorType;
import com.maxim.coupons.dto.ErrorBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler
    @ResponseBody
    public ErrorBean toResponse(Throwable throwable) {

        if (throwable instanceof ApplicationException) {
            ApplicationException appException = (ApplicationException) throwable;

            if (appException.getErrorType().isShowStackTrace()) {
                appException.printStackTrace();
            }

            ErrorType errorType = appException.getErrorType();
            int errorNumber = errorType.getErrorNumber();
            String errorMessage = errorType.getErrorMessage();
            String errorName = errorType.name();
            ErrorBean errorBean = new ErrorBean(errorNumber, errorMessage, errorName);
            //httpServletResponse.setStatus(errorNumber);
            return errorBean;
        }

        throwable.printStackTrace();

        String errorMessage = throwable.getMessage();
        ErrorBean errorBean = new ErrorBean(101, errorMessage, "General error");
        return errorBean;
    }
}
