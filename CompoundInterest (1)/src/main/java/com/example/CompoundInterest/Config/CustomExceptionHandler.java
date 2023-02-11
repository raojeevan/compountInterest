package com.example.CompoundInterest.Config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(value=ArithmeticException.class)
  public ResponseEntity compIntExceptionHandler(ArithmeticException ex){
    List<String> errors = new ArrayList<String>();
    errors.add("Input values are invalid");

    ErrorResponse errorResponse=  ErrorResponse.builder()
        .message(errors)
        .status(HttpStatus.NOT_ACCEPTABLE)
        .devMessage(ex.getMessage())
        .ApplErrorCode("APplicationerror")
        .build();

    return new ResponseEntity(errorResponse,HttpStatus.NOT_ACCEPTABLE);

  }



}
