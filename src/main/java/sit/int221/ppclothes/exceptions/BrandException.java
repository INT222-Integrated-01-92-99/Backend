package sit.int221.ppclothes.exceptions;

public class BrandException extends RuntimeException{

    ExceptionRepo.ERROR_CODE error_code;

    public BrandException(ExceptionRepo.ERROR_CODE error_code, String message){
        super(message);
        this.error_code = error_code;
    }

    public ExceptionRepo.ERROR_CODE getError_code(){
        return error_code;
    }
}
