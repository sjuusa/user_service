package com.sju.myapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadUserInputException extends RuntimeException{
  public BadUserInputException(String exception)
  {
    super(exception);
  }
}
