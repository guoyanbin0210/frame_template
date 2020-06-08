package com.lt.base.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@RestControllerAdvice
@Log4j2
public aspect ExceptionControllerAdvice extends ResponseEntityExceptionHandler {


}
