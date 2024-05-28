package com.anthinhphatjsc.ezisolutions.core.handlers;

import com.anthinhphatjsc.ezisolutions.core.BaseResponse;
import com.anthinhphatjsc.ezisolutions.exceptions.CustomException;
import com.anthinhphatjsc.ezisolutions.exceptions.ErrorEntity;
import com.anthinhphatjsc.ezisolutions.utils.AppErrors;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //  VALIDATION INPUT
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        BaseResponse responseData = new BaseResponse();
        responseData.setStatus(HttpStatus.BAD_REQUEST.value());
        responseData.setMessage("Validation Error");
        responseData.setData(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    // HANDLE CUSTOM EXCEPTION
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException exception) {
        AppErrors errorsApp = exception.getAppError();

        BaseResponse responseData = new BaseResponse();
        responseData.setStatus(errorsApp.getCode());
        responseData.setMessage("Bad request");
        responseData.setData(errorsApp.getDescription());

        return ResponseEntity
                .status(errorsApp.getStatusCode())
                .body(responseData);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleSQLException(SQLIntegrityConstraintViolationException exception) {
        BaseResponse responseData = new BaseResponse();
        responseData.setStatus(HttpStatus.BAD_REQUEST.value());
        responseData.setMessage("SQL Validation Error");
        responseData.setData(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException ex) {
        List<ErrorEntity> errorList = new ArrayList<>();
        ex.getConstraintViolations().forEach((x) -> {
            ErrorEntity errorEntity = ErrorEntity.builder()
                    .code("400")
                    .valueError(x.getInvalidValue().toString())
                    .message(x.getMessage())
                    .build();
            errorList.add(errorEntity);
        });
        return new ResponseEntity<>(
                new BaseResponse(HttpStatus.BAD_REQUEST, errorList), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
