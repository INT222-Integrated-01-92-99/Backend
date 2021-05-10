package sit.int221.ppclothes.exceptions;

import java.time.LocalDateTime;

public class ExceptionRepo {

    public static enum ERROR_CODE {
        PRODUCT_DOES_NOT_EXIST(101),PRODUCT_ALREADY_EXIST(102),
        PRODUCT_ID_ALREADY_EXIST(103), PRODUCT_NAME_ALREADY_EXIST(104);
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
