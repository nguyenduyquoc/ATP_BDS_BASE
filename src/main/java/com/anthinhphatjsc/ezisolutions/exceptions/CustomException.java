package com.anthinhphatjsc.ezisolutions.exceptions;

import com.anthinhphatjsc.ezisolutions.utils.AppErrors;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private AppErrors appError;
    private Integer errorCode;

    public CustomException(AppErrors appError) {
        super(appError.getDescription());
        this.appError = appError;
        this.errorCode = appError.getCode();
    }


    public CustomException(int code, String mess) {
        super(mess);
        errorCode = code;
    }

    public CustomException(String mess) {

        super(mess);
    }

    @Override
    public String getMessage() {

        return super.getMessage();
    }

}

