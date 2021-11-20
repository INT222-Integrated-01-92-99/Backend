package sit.int221.ppclothes.exceptions;

public class AccountException extends RuntimeException{

    ExceptionRepo.ERROR_CODE error_code;

    public AccountException(ExceptionRepo.ERROR_CODE error_code, String message){
        super(message);
        this.error_code = error_code;
    }

    public ExceptionRepo.ERROR_CODE getError_code(){
        return error_code;
    }
}
