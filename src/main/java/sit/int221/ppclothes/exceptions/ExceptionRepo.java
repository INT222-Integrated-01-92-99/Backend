package sit.int221.ppclothes.exceptions;

import java.time.LocalDateTime;

public class ExceptionRepo {

    public static enum ERROR_CODE {
        PRODUCT_DOES_NOT_EXIST(1001),
        PRODUCT_ALREADY_EXIST(1002),
        PRODUCT_ID_ALREADY_EXIST(1003),
        PRODUCT_NAME_ALREADY_EXIST(1004),
        AMOUNT_VALUE(1005),
        PRODUCT_IMAGE_NULL(1006),

        ACCOUNT_USERNAME_INCORRECT(2001),
        ACCOUNT_PASSWORD_INCORRECT(2002),
        USERNAME_HAVE_ALREADY(2003),

        BRAND_NAME_HAVE_ALREADY(3001);
        private int errorValue;

        ERROR_CODE(int errorValue){
            this.errorValue = errorValue;
        }

    }
    private ERROR_CODE errorCode;
    private String errorMessage;
    private LocalDateTime errorDateTime;

    public ExceptionRepo(ERROR_CODE errorCode, String errorMessage, LocalDateTime errorDateTime) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDateTime = errorDateTime;
    }

    public ERROR_CODE getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getErrorDateTime() {
        return errorDateTime;
    }
}
