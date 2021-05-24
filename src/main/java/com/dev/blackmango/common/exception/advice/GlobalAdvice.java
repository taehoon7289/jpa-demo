package com.dev.blackmango.common.exception.advice;

import com.dev.blackmango.common.exception.ServiceException;
import com.dev.blackmango.common.model.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdvice {

  @ExceptionHandler(ServiceException.class)
  public ResponseData handlerException(ServiceException e) {
    return new ResponseData(e.getCode(), e.getMessage());
  }
}
