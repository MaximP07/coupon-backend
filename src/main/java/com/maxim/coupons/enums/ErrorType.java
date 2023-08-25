package com.maxim.coupons.enums;

public enum ErrorType {
    GENERAL_ERROR(101, "General error", true),
    INVALID_PASSWORD(102, "password is invalid. Enter a password with between 6 to 10 letters or numbers or both", false),
    INVALID_PRICE(103, "Coupon price must be more than 0 and less then 100", false),
    INVALID_EMAIL(104, "Email address is InValid, Please enter a valid email address", false),
    INVALID_USER_TYPE(105, "Your user type is not possible with the company-id parameter you entered", true),
    INVALID_COUPON_NAME(108, "The coupon name must be between 4 to 15 letters or numbers or both", false),
    INVALID_COMPANY_ID(109, "You must enter the ID of an existing company", false),
    INVALID_DESCRIPTION(111, "The description must be between 10 to 100 letters or numbers or both", false),
    INVALID_SQL_QUERY(112, "You have an error in your SQL syntax", true),
    INVALID_COMPANY_COUNTY_LENGTH(113, " company country length must be 20 letters", false),
    INVALID_PURCHASE_COUPON_ID(114, " purchase couponID must be positive number", false),
    INVALID_PURCHASE_USER_ID(115, " purchase userID must be positive number", false),
    INVALID_PURCHASE_AMOUNT(116, " purchase amount must be positive number", false),
    INVALID_AMOUNT_OF_COUPONS(117, " coupon amount must be positive number", false),
    INVALID_CATEGORY(118, " coupon category must not null", false),
    INVALID_START_DATE_OR_END_DATE_OF_THE_COUPON(119, " coupon start date must be before end date", false),
    INVALID_PURCHASE_COMPANY_ID(120, " purchase companyID must be positive number", false),
    INVALID_COMPANY_NAME(121, "Incorrect company name", false),
    FAILED_TO_LOGIN(122, "Failed login, please try again", true);


    private int errorNumber;
    private String errorMessage;
    private boolean isShowStackTrace;

    private ErrorType(int errorNumber, String internalMessage, boolean isShowStackTrace) {
        this.errorNumber = errorNumber;
        this.errorMessage = internalMessage;
        this.isShowStackTrace = isShowStackTrace;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isShowStackTrace() {
        return isShowStackTrace;
    }
}
